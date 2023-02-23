package com.empleados.empleados.application.impl;

import com.empleados.empleados.application.DeleteEmpleadoPort;
import com.empleados.empleados.domain.Empleado;
import com.empleados.empleados.infraestructure.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteEmpleadoImpl implements DeleteEmpleadoPort {
    @Autowired
    EmpleadoRepository empleadoRepository;



    @Override
    public String deleteEmpleado(Long id) {
        Optional<Empleado> empleadoOptional = empleadoRepository.findById(id);
        if(empleadoOptional.isEmpty()){
            throw new RuntimeException("Empleado no existe");
        }
        empleadoRepository.delete(empleadoOptional.get());
        return "Empleado Eliminado";
    }
}
