package com.formacion.BS7_2.student.infraestructure.controller;

import com.formacion.BS7_2.student.application.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student/remove")
public class RemoveSubject {

    @Autowired
    IStudentService studentService;
    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable String id) throws Exception{
        studentService.deleteStudent(id);
    }
}
