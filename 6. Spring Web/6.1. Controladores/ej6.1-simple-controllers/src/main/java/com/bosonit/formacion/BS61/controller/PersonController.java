package com.bosonit.formacion.BS61.controller;

import com.bosonit.formacion.BS61.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/useradd")
public class PersonController {


    @PostMapping("/add")
    public Person showPerson(@RequestBody Person person){
        person.setAge(person.getAge()+1);
        return person;



    }
}
