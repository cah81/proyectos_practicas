package com.formacion.bosonit.ej6_2.controllers;

import com.formacion.bosonit.ej6_2.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeanPerson {

    @Autowired
    Person person1;
    @Autowired
    Person person2;
    @Autowired
    Person person3;


    @GetMapping("controlador/bean/bean1")
    @Qualifier("Bean1")
    public Person person1(){
        person1.setName("carlos");
        person1.setCity("madrid");
        person1.setAge(40);
        return person1;
    }


    @GetMapping("controlador/bean/bean2")
    @Qualifier("Bean2")
    public Person person2(){
        person2.setName("Jose");
        person2.setCity("valencia");
        person2.setAge(25);
        return person2;
    }


    @GetMapping("controlador/bean/bean3")
    @Qualifier("Bean3")
    public Person person3(){
        person3.setName("maria");
        person3.setCity("burgos");
        person3.setAge(35);
        return person3;
    }
}
