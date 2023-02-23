package com.empleados.empleados.application;

import com.empleados.empleados.domain.Empleado;

public interface UpdateEmpleadoPort {

    public Empleado updateEmpleado(Long id, Empleado empleado);
}
