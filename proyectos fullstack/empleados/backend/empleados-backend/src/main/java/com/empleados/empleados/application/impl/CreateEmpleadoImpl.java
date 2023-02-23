package com.empleados.empleados.application.impl;

import com.empleados.empleados.application.CreateEmpleadoPort;
import com.empleados.empleados.domain.Empleado;
import com.empleados.empleados.infraestructure.repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateEmpleadoImpl implements CreateEmpleadoPort {

    private final EmpleadoRepository empleadoRepository;

    @Override
    public Empleado crearEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }
}
