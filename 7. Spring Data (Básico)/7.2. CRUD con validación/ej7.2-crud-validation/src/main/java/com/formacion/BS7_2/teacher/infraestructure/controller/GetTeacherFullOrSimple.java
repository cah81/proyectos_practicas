package com.formacion.BS7_2.teacher.infraestructure.controller;

import com.formacion.BS7_2.teacher.application.ITeacherService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class GetTeacherFullOrSimple {

    @Autowired
    ITeacherService teacherService;

    @GetMapping("/teacher/person/{id}")
    public ResponseEntity showByNameStudentPersonId(@PathVariable String id, @RequestParam(name = "outputType", defaultValue = "simple") @NotNull String outputType) throws Exception {
        if (outputType.equals("simple")) {
            return new ResponseEntity<>(teacherService.findById(id), HttpStatus.OK);
        } else if (outputType.equals("full")) {
            return new ResponseEntity<>(teacherService.GetTeacherPerson(id), HttpStatus.OK);
        } else return new ResponseEntity<>("Error param", HttpStatus.BAD_REQUEST);
    }

}
