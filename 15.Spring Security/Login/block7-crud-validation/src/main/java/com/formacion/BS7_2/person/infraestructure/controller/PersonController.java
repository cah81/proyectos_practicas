package com.formacion.BS7_2.person.infraestructure.controller;

import com.formacion.BS7_2.person.application.services.PersonService;
import com.formacion.BS7_2.person.infraestructure.dto.input.PersonInputDto;
import com.formacion.BS7_2.person.infraestructure.dto.output.PersonOutputDto;
import com.formacion.BS7_2.role.application.RoleService;
import com.formacion.BS7_2.role.infraestructure.dto.RoleInputDto;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/person")

public class PersonController {
    @Autowired
    PersonService personService;




    @PostMapping("/addPerson")
    public PersonOutputDto addPerson(@RequestBody PersonInputDto personInputDto) throws Exception {
        return personService.addUser(personInputDto);
    }
    @DeleteMapping("/delete/{id}")
    public String deletePerson(@PathVariable Integer id) throws Exception {
        personService.deleteUser(id);
        return "User deleted";
    }

    @GetMapping("/personList")
    public List<PersonOutputDto> findall(){
        return personService.findALl();
    }

    @GetMapping("/person/{id}")
    public PersonOutputDto showById(@PathVariable Integer id) throws Exception {
        return personService.findById(id);
    }
    @PutMapping("/update/{id}")
    public PersonOutputDto updatePerson(@PathVariable Integer id, @RequestBody PersonInputDto personInputDto) throws Exception {
        return personService.updateUser(id,personInputDto);
    }
    @GetMapping("/person/username/{username}")
    public PersonOutputDto showByName(@PathVariable("username") String username) throws Exception {
        return personService.findByName(username);
    }




    @PostMapping("/role/addToPerson")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {


    }


    @Data
    class RoleToPersonForm{
        private String username;
        private String roleName;
    }


}


