package com.formacion.bosonit.ej6_2.controllers;

import com.formacion.bosonit.ej6_2.models.City;
import com.formacion.bosonit.ej6_2.models.Person;
import com.formacion.bosonit.ej6_2.models.interfaces.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/controlador1")
public class Controller1 {
   
     @Autowired
     IPersonService service;

     @Autowired
     List<City> cityList;



    @GetMapping("/addPerson")
    public Person getPerson(@RequestHeader("name")String name , @RequestHeader("city") String city,@RequestHeader("age") int age){

        service.miObject().setName(name);
       service.miObject().setCity(city);
       service.miObject().setAge(age);

       return service.miObject();

    }
    @PostMapping("/addCiudad")
    public List<City> addCity(@RequestHeader ("name") String name,@RequestHeader("nPopulation") int population)
    {
        City city1 = new City(name,population);
        cityList.add(city1);
        return cityList;
    }


}



