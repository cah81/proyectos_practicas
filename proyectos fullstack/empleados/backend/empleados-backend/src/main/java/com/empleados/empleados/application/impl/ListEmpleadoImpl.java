package com.empleados.empleados.application.impl;

import com.empleados.empleados.application.ListEmpleadosPort;
import com.empleados.empleados.domain.Empleado;
import com.empleados.empleados.infraestructure.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ListEmpleadoImpl implements ListEmpleadosPort {

    @Autowired
    EmpleadoRepository empleadoRepository;


    @Override
    public List<Empleado> findAll() {
        List<Empleado> empleadoList = new ArrayList<>();
        empleadoList = empleadoRepository.findAll();
        return empleadoList;
    }
}
