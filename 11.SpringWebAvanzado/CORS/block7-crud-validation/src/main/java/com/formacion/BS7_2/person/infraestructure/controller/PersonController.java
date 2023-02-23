package com.formacion.BS7_2.person.infraestructure.controller;

import com.formacion.BS7_2.person.application.services.PersonService;
import com.formacion.BS7_2.person.infraestructure.dto.input.PersonInputDto;
import com.formacion.BS7_2.person.infraestructure.dto.output.PersonOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PersonController {
    @Autowired
    PersonService personService;



    @CrossOrigin("*")
    @PostMapping("/addperson")
    public PersonOutputDto addPerson(@RequestBody PersonInputDto personInputDto) throws Exception {
        return personService.addUser(personInputDto);
    }
    @DeleteMapping("/delete/{id}")
    public String deletePerson(@PathVariable Integer id) throws Exception {
        personService.deleteUser(id);
        return "User deleted";
    }

    @GetMapping("/getall")
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
    public List<PersonOutputDto> showByName(@PathVariable("username") String username) throws Exception {
        return personService.findByName(username);
    }




}


