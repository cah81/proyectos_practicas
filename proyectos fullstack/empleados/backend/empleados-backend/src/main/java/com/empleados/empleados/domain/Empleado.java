package com.empleados.empleados.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "empleados")
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre",length = 60,nullable = false)
    private String nombre;
    @Column(name = "apellido",length = 60,nullable = false)
    private String apellido;
    @Column(name = "email",unique = true,nullable = false)
    private String email;
}
