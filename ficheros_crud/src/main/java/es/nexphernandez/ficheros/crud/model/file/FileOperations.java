package es.nexphernandez.ficheros.crud.model.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
/**
 * @author nexphernandez
 * @version 1.0.0
 */
import java.util.Set;

import es.nexphernandez.ficheros.crud.Interface.Operations;
import es.nexphernandez.ficheros.crud.model.Empleado;

public class FileOperations implements Operations {
    File fichero;
    String path = "/home/dam/Documentos/Ficheros-CRUD/ficheros_crud/src/main/resources/empleados.txt";

    /**
     * Constructor de la clase
     */
    public FileOperations(){
        this.fichero = new File(this.path);
        if (!this.fichero.exists() || !this.fichero.isFile()) {
            throw new IllegalArgumentException("El recurso no es de tipo fichero " + this.path + ".");
        }
    }
    
    /**
     * Funcion para aniadir un empleado al fichero
     * @param empleado empleado a aniadir
     * @return true/false
     */
    @Override
    public boolean create(Empleado empleado) {
        if (empleado == null || empleado.getIdentificador() == null || empleado.getIdentificador().isEmpty()) {
            return false;
        }
        Set<Empleado> empleados = read(fichero);
        if (empleados.contains(empleado)) {
            return false;
        }
        return create(empleado.toString(),fichero);
    }

    /**
     * Funcion para aniadir informacion al fichero
     * @param data informacion a aniadir
     * @param file fichero donde aniadir
     * @return true/false
     */
    private boolean create (String data, File file){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file,true))){
            writer.write(data);
            writer.newLine();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Lee la informacion del archivo
     * @param file donde se encuentra la informacion
     * @return Set<Empleado>
     */
    private Set<Empleado> read(File file){
        if (file == null) {
            return new HashSet<>();
        }
        Set<Empleado> empleados = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = reader.readLine()) != null) {
                String [] arrayLine = line.trim().split(",");
                Empleado empleado = new Empleado(arrayLine[0], arrayLine[1], arrayLine[2], Double.valueOf(arrayLine[3]), arrayLine[4]);
                empleados.add(empleado);
            }
        } catch (IOException e) {
            return new HashSet<>();
        }
        return empleados;
    }

    /**
     * Lee un empleado por su identificador
     * @param identificador identificador del empleado
     * @return empleado
     */
    @Override
    public Empleado read(String identificador) {
        if (identificador == null || identificador.isEmpty()) {
            return null;
        }
        Empleado empleado = new Empleado(identificador);
        return read(empleado);
    }

    /**
     * Metodo que lee la informacion de un empleado
     * @param empleado a leer
     * @return true/false
     */
    @Override
    public Empleado read(Empleado empleado) {
       Set<Empleado> empleados = read(fichero);
       for (Empleado empleado2 : empleados) {
            if (empleado2.equals(empleado)) {
                return empleado2;
            }
       }
       return null;
    }

    /**
     * Metodo que actualiza la informacion de un empleado
     * @param empleado a actualizar
     * @return true/false
     */
    @Override
    public boolean update(Empleado empleado) {
        if (empleado == null || empleado.getIdentificador().isEmpty() || empleado.getIdentificador() == null) {
            return false;
        }
        Set<Empleado> empleados = read(fichero);
        boolean encontrado = false;
        Set<Empleado> actualizados = new HashSet<>();

        for (Empleado emp : empleados) {
            if (emp.equals(empleado)) {
                actualizados.add(empleado);
                encontrado = true;
            } else {
                actualizados.add(emp);
            }
        }

        if (!encontrado) {
            return false;
        }

        return updateFile(actualizados, fichero);
    }

    /**
     * Actualiza la informacion del ficher0
     * @param empleados de la empresa
     * @param file fichero con la informacion
     * @return true/false
     */
    private boolean updateFile (Set<Empleado> empleados, File file){
        String path = file.getAbsolutePath();
        try {
            file.delete();
            file.createNewFile();
        } catch (IOException e) {
            return false;
        }
        for (Empleado empleado : empleados) {
            create(empleado);
        }
        return true;
    }

    /**
     * Elimina a un empleado de la empresa
     * @param identificador del empleado a eliminar
     * @return true/false
     */
    @Override
    public boolean delete(String identificador) {
        if (identificador == null || identificador.isEmpty()) {
            return false;
        }
        Empleado empleado = new Empleado(identificador);
        Set<Empleado> empleados = read(fichero);
        if (!empleados.contains(empleado)) {
            return false;
        }
        empleados.remove(empleado);
        return updateFile(empleados, fichero);
    }

    /**
     * Devuelve una lista de empleados de un puesto
     * @param puesto del empleado
     * @return lista de empleado
     */
    @Override
    public Set<Empleado> empleadosPorPuesto(String puesto) {
        if (puesto == null || puesto.isEmpty()) {
            return new HashSet<>();
        }
        Set<Empleado> empleados = read(fichero);
        Set<Empleado> porPuesto = new HashSet<>();
        for (Empleado empleado : empleados) {
            if (empleado.getPuesto().trim().equals(puesto.trim())) {
                porPuesto.add(empleado);
            }
        }
        return porPuesto;
    }

    /**
     * Devuelve una lista de empleados por edad
     * @param fechaInicio del intervalo
     * @param fechaFin del intervalo
     * @return lista de empleado
     */
    @Override
    public Set<Empleado> empleadosPorEdad(String fechaInicio, String fechaFin) {
        if (fechaInicio == null || fechaInicio.isEmpty() || fechaFin == null || fechaFin.isEmpty()) {
            return new HashSet<>();
        }
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate inicio = LocalDate.parse(fechaInicio,formato);
        LocalDate fin = LocalDate.parse(fechaFin, formato);
        Set<Empleado> empleados = read(fichero);
        Set<Empleado> porEdad = new HashSet<>();
        for (Empleado empleado : empleados) {
            LocalDate cumpleanio = LocalDate.parse(empleado.getFechaDeNacimiento().trim(),formato);
            if ((cumpleanio.equals(inicio) || cumpleanio.isAfter(inicio)) && cumpleanio.isBefore(fin) ) {
                porEdad.add(empleado);
            }
        }
        return porEdad;
    }
    
}
