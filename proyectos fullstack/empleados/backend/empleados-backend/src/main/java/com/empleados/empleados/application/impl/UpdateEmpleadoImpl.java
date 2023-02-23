package com.empleados.empleados.application.impl;

import com.empleados.empleados.application.UpdateEmpleadoPort;
import com.empleados.empleados.domain.Empleado;
import com.empleados.empleados.infraestructure.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateEmpleadoImpl implements UpdateEmpleadoPort {

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Override
    public Empleado updateEmpleado(Long id, Empleado empleadoInput) {
        Empleado empleadoUpdate;
        Optional<Empleado> empleadoActual= empleadoRepository.findById(id);
        if(empleadoActual.isEmpty()){
            throw new RuntimeException("empleado no existe");
        }
        empleadoUpdate = empleadoActual.get();
        empleadoUpdate.setNombre(empleadoInput.getNombre());
        empleadoUpdate.setApellido(empleadoInput.getApellido());
        empleadoUpdate.setEmail(empleadoInput.getEmail());
        empleadoRepository.save(empleadoUpdate);
        return empleadoUpdate;
    }
}
