package com.formacion.BS7_1.services;

import com.formacion.BS7_1.model.Person;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPersonServices {

    public Person findById(Integer id);
    public Person findByName(String name);

    public Person addPerson( Person person);

    public Person update(Person personUpdate,Person person);

    public Boolean removePerson(Integer id);



}
