package com.formacion.BS7_2.role.infraestructure.dto;

import com.formacion.BS7_2.role.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleOutputDto {

    private String name;

    public RoleOutputDto (Role role){

        this.name = role.getName();

    }


}
