package com.empleados.empleados.infraestructure.repository;

import com.empleados.empleados.domain.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {
}
