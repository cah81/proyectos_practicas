package com.empleados.empleados.application.impl;

import com.empleados.empleados.application.GetEmpleadoByIdPort;
import com.empleados.empleados.domain.Empleado;
import com.empleados.empleados.infraestructure.repository.EmpleadoRepository;
import com.empleados.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindEmpleadoByIdImpl implements GetEmpleadoByIdPort {
    @Autowired
    EmpleadoRepository empleadoRepository;


    @Override
    public Empleado findEmpleadoById(Long id) {
        return empleadoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Empleado no existe"));
    }
}
