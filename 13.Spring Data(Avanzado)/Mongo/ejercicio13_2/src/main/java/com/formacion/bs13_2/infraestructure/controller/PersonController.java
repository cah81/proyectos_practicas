package com.formacion.bs13_2.infraestructure.controller;

import com.formacion.bs13_2.application.IPersonService;
import com.formacion.bs13_2.domain.Person;
import com.formacion.bs13_2.infraestructure.dto.PersonInputDto;
import com.formacion.bs13_2.infraestructure.dto.PersonOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    IPersonService personService;


    @PostMapping("/add")
    public PersonOutputDto createPerson(@RequestBody PersonInputDto personInputDto){
        return personService.createPerson(personInputDto);
    }

    @PutMapping("/{id}")
    public PersonOutputDto updatePerson(@RequestBody PersonInputDto personInputDto, @PathVariable Integer id){
        return personService.updatePerson(personInputDto,id);
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable Integer id){
        personService.deletePerson(id);
        return "person deleted";
    }

    @GetMapping("/{id}")
    public PersonOutputDto getPersonById(@PathVariable Integer id){
        return personService.getPersonById(id);
    }

    @GetMapping("/all")
    public List<PersonOutputDto> getAllPerson(){
        return personService.getAllPerson();
    }

    @GetMapping("/allPaginated")
    public List<PersonOutputDto> getAllPersonPaginable(@RequestParam("pageSize") int pageSize,
                                                       @RequestParam("numPage")int numPage){
        return personService.getAllPersonPage(pageSize,numPage);

    }
}
