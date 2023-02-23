package com.formacion.BS7_2.teacher.infraestructure.controller;

import com.formacion.BS7_2.teacher.application.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class DeleteTeacher {
    @Autowired
    ITeacherService teacherService;
    @DeleteMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable String id) throws Exception{
        teacherService.deleteTeacher(id);
        return "teacher deleted";
    }
}
