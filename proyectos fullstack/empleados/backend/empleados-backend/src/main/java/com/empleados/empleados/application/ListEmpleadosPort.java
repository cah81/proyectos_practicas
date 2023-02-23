package com.empleados.empleados.application;

import com.empleados.empleados.domain.Empleado;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ListEmpleadosPort {

    public List<Empleado> findAll();
}
