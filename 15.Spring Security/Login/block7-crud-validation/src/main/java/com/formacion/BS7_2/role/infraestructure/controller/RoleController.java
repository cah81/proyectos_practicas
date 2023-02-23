package com.formacion.BS7_2.role.infraestructure.controller;

import com.formacion.BS7_2.person.application.services.PersonService;

import com.formacion.BS7_2.person.infraestructure.dto.input.PersonInputDto;
import com.formacion.BS7_2.person.infraestructure.dto.output.PersonOutputDto;
import com.formacion.BS7_2.role.application.RoleService;

import com.formacion.BS7_2.role.domain.Role;
import com.formacion.BS7_2.role.infraestructure.dto.RoleInputDto;

import com.formacion.BS7_2.role.infraestructure.dto.RoleOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
    private final PersonService personService;

    @PostMapping("/addRole")
    public String addPerson(@RequestBody RoleInputDto roleInputDto) throws Exception {

        roleService.addRoles(roleInputDto);
        return "role saved";
    }

    @GetMapping("/listRole")
    public List<RoleOutputDto> roleList() throws Exception {

       return  roleService.ShowAllRoles();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRole(@PathVariable Long id) throws Exception {
        roleService.deleteRoles(id);
        return "User deleted";
    }

    @PutMapping("/update/{id}")
    public RoleOutputDto updateRole(@PathVariable Long id, @RequestBody RoleInputDto roleInputDto) throws Exception {
        return roleService.updateRole(roleInputDto,id);
    }

    @GetMapping("/roleName/{roleName}")
    public RoleOutputDto showByRoleName(@PathVariable("roleName") String roleName) throws Exception {
        return roleService.findByRoleName(roleName);
    }

    @PostMapping("/addRolePerson/{username}/{roleInputDto}")
    public String addRoleToPerson(@PathVariable("username") String username,@PathVariable String roleInputDto) throws Exception{
        roleService.addRolePerson(username,roleInputDto);
        return "role added";
    }

    @DeleteMapping("/deleteRolePerson/{username}/{id}")
    public String deleteRoleToPerson(@PathVariable("username") String username,@PathVariable Long id) throws Exception{
        roleService.deleteRolePerson(username,id);
        return "role deleted";

    }
    @GetMapping("/role/addtoPerson")
    public void refreshtoken(HttpServletRequest request, HttpServletResponse response){
        String authorizationHeader = request.getHeader(AUTHORIZATION);
    }





}
