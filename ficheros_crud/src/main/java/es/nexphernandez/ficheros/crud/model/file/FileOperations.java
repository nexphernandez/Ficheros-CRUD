package es.nexphernandez.ficheros.crud.model.file;

import java.io.File;
/**
 * @author nexphernandez
 * @version 1.0.0
 */
import java.util.Set;

import es.nexphernandez.ficheros.crud.Interface.Operations;
import es.nexphernandez.ficheros.crud.model.Empleado;

public class FileOperations implements Operations {
    File fichero;
    String path = "ficheros_crud\\src\\main\\resources\\empleados.txt";

    @Override
    public boolean create(Empleado empleado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Empleado read(String identificador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

    @Override
    public Empleado read(Empleado empleado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

    @Override
    public boolean update(Empleado empleado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(String identificcador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Set<Empleado> empleadosPorPuesto(String puesto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'empleadosPorPuesto'");
    }

    @Override
    public Set<Empleado> empleadosPorEdad(String fechaInicio, String fechaFin) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'empleadosPorEdad'");
    }
    
}
