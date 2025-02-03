package es.nexphernandez.ficheros.crud.Interface;
/**
 * @author nexphernandez
 * @version 1.0.0
 */

import java.util.Set;

import es.nexphernandez.ficheros.crud.model.Empleado;

public interface Operations {
    public boolean create (Empleado empleado);
    public Empleado read (String identificador);
    public Empleado read (Empleado empleado);
    public boolean update (Empleado empleado);
    public boolean delete (String identificcador);
    public Set<Empleado> empleadosPorPuesto (String puesto);
    public Set<Empleado> empleadosPorEdad (String fechaInicio, String fechaFin);
}
