package es.nexphernandez.ficheros.crud.model;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
/**
 * @author nexpherandez
 * @version 1.0.0
 */
public class Empleado {
    private String identificador;
    private String nombre;
    private String puesto;
    private double salario;
    private String fechaDeNacimiento;

    /**
     * Constuctor vacio
     */
    public Empleado(){}

    /**
     * Constructor con el atributo clave de la clase
     * @param identificador identificador del empleado
     */
    public Empleado(String identificador){
        this.identificador = identificador;
    }

    /**
     * Constructor de los atributos de la clase
     * @param identificador identificador del empleado
     * @param nombre nombre del empleado
     * @param puesto puesto del empleado
     * @param salario salario del emplado
     * @param fechaNacimiento fecha de nacimiento del empleado
     */
    public Empleado(String identificador, String nombre, String puesto, 
    double salario, String fechaNacimiento){
        this.identificador = identificador;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        this.fechaDeNacimiento = fechaNacimiento;
    }

    public String getIdentificador() {
        return this.identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return this.puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getSalario() {
        return this.salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getFechaDeNacimiento() {
        return this.fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    /**
     * Funcion que te da la edad el emplado respecto al nacimiento
     * @param fechaNacimiento fecha de nacimiento del empleado
     * @return la edad del empleado
     */
    public int getEdad(String fechaNacimiento){
        LocalDate localDate = LocalDate.now();
        String[] arrayFechaNacimiento = fechaNacimiento.split("/");
        int anio = Integer.parseInt(arrayFechaNacimiento[2]);
        int mes = Integer.parseInt(arrayFechaNacimiento[1]);
        int dias = Integer.parseInt(arrayFechaNacimiento[0]);
        LocalDate nacimiento = localDate.of(anio,mes,dias);
        Period edad = Period.between(localDate, nacimiento);
        return (int)edad.getYears();
    }

    /**
     * Funcion equals de la clase
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Empleado)) {
            return false;
        }
        Empleado empleado = (Empleado) o;
        return Objects.equals(identificador, empleado.identificador);
    }

    /**
     * Funcion hashCode de la clase
     */
    @Override
    public int hashCode() {
        return Objects.hash(identificador);
    }

    /**
     * Funcion toString de la clase
     */
    @Override
    public String toString() {
        return getIdentificador() + ", " +
             getNombre() + ", " +
             getPuesto() + ", " +
             getSalario() + ", " +
             getFechaDeNacimiento();
    }
    

}
