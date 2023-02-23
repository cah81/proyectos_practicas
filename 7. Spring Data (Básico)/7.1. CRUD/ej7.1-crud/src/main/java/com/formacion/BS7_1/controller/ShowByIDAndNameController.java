package com.formacion.BS7_1.controller;

import com.formacion.BS7_1.model.Person;
import com.formacion.BS7_1.services.IPersonServices;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("persona")
public class ShowByIDAndNameController {

    @Autowired
    IPersonServices personServices;
    @Autowired
    private List<Person> personList;

    @GetMapping("/{id}")
    public Person findById(@PathVariable Integer id){
        return personServices.findById(id);
    }

    @GetMapping("/nombre/{name}")
    public Person findByName(@PathVariable("name") String name){
        return personServices.findByName(name);
    }
}
