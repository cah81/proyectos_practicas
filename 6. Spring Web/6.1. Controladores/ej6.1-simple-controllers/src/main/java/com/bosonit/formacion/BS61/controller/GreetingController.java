package com.bosonit.formacion.BS61.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class GreetingController {

    @GetMapping("/{nameUser}")
    public String greeting(@PathVariable String nameUser){
        return "Hola " + " " + nameUser;
    }

}
