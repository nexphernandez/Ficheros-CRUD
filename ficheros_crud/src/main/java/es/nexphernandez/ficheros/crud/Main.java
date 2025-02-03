package es.nexphernandez.ficheros.crud;

import es.nexphernandez.ficheros.crud.model.Empleado;
import es.nexphernandez.ficheros.crud.model.file.FileOperations;

public class Main {
    public static void main(String[] args) {
        Empleado empleado = new Empleado("1", "Nico", "Director", 50000,"13/10/1994");
        System.out.println(empleado);
        System.out.println(empleado.getEdad(empleado.getFechaDeNacimiento()));

        FileOperations operaciones = new FileOperations();
        boolean insertar = operaciones.create(empleado);
        if (insertar) {
            System.out.println("Se ha insertado correctamente");
        }  else{
            System.out.println("No se ha insertado el elemento");
        }
        Empleado personaBuscar = new Empleado("1");
        personaBuscar = operaciones.read(personaBuscar);
        System.out.println("Persona encontrada: " + personaBuscar);
        Empleado personaBuscar2 = new Empleado("1","Nicolas","Accionista",100000,"14/11/1997");
        System.out.println(operaciones.update(personaBuscar2));
        System.out.println(operaciones.empleadosPorPuesto("Accionista"));
        System.out.println(operaciones.empleadosPorEdad("01/01/1970", "01/01/2024"));
    }   
}