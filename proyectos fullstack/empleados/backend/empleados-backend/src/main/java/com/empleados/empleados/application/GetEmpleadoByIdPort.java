package com.empleados.empleados.application;

import com.empleados.empleados.domain.Empleado;

public interface GetEmpleadoByIdPort {

    public Empleado findEmpleadoById(Long id);

}
