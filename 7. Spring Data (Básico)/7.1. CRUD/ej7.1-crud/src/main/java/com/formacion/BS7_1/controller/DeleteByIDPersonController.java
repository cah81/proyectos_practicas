package com.formacion.BS7_1.controller;

import com.formacion.BS7_1.model.Person;
import com.formacion.BS7_1.services.IPersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("persona")
public class DeleteByIDPersonController {
    @Autowired
    IPersonServices personServices;
    @Autowired
    List<Person> personList;

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable ("id")Integer id) {
        if(personServices.removePerson(id)){
            return "The person with id " + id + " has been succesfully eliminated";
        }
        return "The Person  with id" + id + "could not be deleted, because it was in the list";
    }

}
