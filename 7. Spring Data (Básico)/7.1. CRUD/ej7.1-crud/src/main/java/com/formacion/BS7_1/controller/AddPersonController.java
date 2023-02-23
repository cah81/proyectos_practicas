package com.formacion.BS7_1.controller;

import com.formacion.BS7_1.model.Person;
import com.formacion.BS7_1.services.IPersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("persona")
public class AddPersonController {
    @Autowired
    IPersonServices personServices;



    //agregando personas a una lista
    @PostMapping
    public ResponseEntity<?>createPerson(@Valid @RequestBody Person person){
        Person personNew = null;
        Map<String,Object> response = new HashMap<>();
        try{
            personNew = personServices.addPerson(person);
        }catch (DataAccessException e){
            response.put("message","error al hacer la creacion");
            response.put("error",e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
        }
        response.put("mensaje","la persona se creo con exito");
        response.put("persona",personNew);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }




}
