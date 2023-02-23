package com.formacion.bosonit.ej6_2.controllers;

import com.formacion.bosonit.ej6_2.models.City;
import com.formacion.bosonit.ej6_2.models.Person;
import com.formacion.bosonit.ej6_2.models.interfaces.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/controlador2")
public class Controller2 {

    @Autowired
    IPersonService service;

    @Autowired
    List<City> cityList;

    private Person person;

    @GetMapping("/getPersona")
    public Person getPerson() {

        service.miObject().setAge(service.miObject().getAge()*2);
        return service.miObject();
    }
    @GetMapping("controlador1/getCiudad")
    public List<City> showListCities(){
        return cityList;

    }
}
