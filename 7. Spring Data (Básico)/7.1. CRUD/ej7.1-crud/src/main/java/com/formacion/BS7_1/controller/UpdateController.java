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
import java.util.Optional;

@RestController
@RequestMapping("persona")
public class UpdateController {

    @Autowired
    IPersonServices personServices;

    @Autowired
    List<Person> personList;

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePerson(@Valid @PathVariable("id")Integer id,@Valid @RequestBody Person person){
        Person personNow = personServices.findById(id);
        Person personUpdate = null;
        Map<String,Object> response = new HashMap<>();
        if (personNow==null){
            response.put("message","error: no se puede editar, el id  ".concat(id.toString().concat("no existe")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);

        }
        try{
            personServices.update(personNow,person);

        }catch (DataAccessException e){
            response.put("message","error al actualizar");
            response.put("error",e.getMessage().concat(":  ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
            response.put("mensaje","El cliente ha sido actualizado");
            response.put("person",personNow);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }





}
