package com.formacion.BS7_2.role.application;

import com.formacion.BS7_2.person.infraestructure.dto.output.PersonOutputDto;
import com.formacion.BS7_2.role.domain.Role;
import com.formacion.BS7_2.role.infraestructure.dto.RoleInputDto;
import com.formacion.BS7_2.role.infraestructure.dto.RoleOutputDto;

import java.util.List;

public interface RoleService {
    public String addRoles(RoleInputDto roleInputDto) throws Exception;
    public String addRolePerson(String username,String roleName) throws  Exception;
    public List<RoleOutputDto> ShowAllRoles() throws Exception;
    public String deleteRoles(Long id) throws Exception;

    public RoleOutputDto updateRole(RoleInputDto roleInputDto, Long id);

    public RoleOutputDto findByRoleName(String roleName)throws Exception;

    public String deleteRolePerson(String username, Long  id) throws  Exception;
}
