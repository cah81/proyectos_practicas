package com.formacion.BS7_2.student.infraestructure.controller;

import com.formacion.BS7_2.student.application.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class DeleteStudent {
    @Autowired
    IStudentService studentService;
    @DeleteMapping("/delete/{id}")
    public String deleteStudents(@PathVariable String id) throws Exception {
        studentService.deleteStudent(id);
        return "Student deleted";
    }
}
