package com.formacion.BS7_2.student.infraestructure.controller;

import com.formacion.BS7_2.student.application.IStudentService;

import com.formacion.BS7_2.student.infraestructure.dto.output.StudentOutputDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student/add")
public class AddSubjectStudent {
    @Autowired
    IStudentService studentService;
    @PutMapping("/{id}")
    public String addSubjectToStudent(@PathVariable String id, @RequestBody List<String> subjects){
        return studentService.addSubjectFromStudent(id,subjects);

    }
}
