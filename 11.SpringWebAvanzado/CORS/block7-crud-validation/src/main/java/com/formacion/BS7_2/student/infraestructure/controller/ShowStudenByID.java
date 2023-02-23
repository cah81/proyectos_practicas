package com.formacion.BS7_2.student.infraestructure.controller;

import com.formacion.BS7_2.student.application.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class ShowStudenByID {
    @Autowired
    IStudentService studentService;
    @GetMapping("/student/{id}")
    public ResponseEntity showById(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);


    }
}
