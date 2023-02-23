package com.formacion.BS7_2.studentSubject.infraestructure.controller;

import com.formacion.BS7_2.studentSubject.application.service.IStudentSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subject")
public class DeleteSubject {
    @Autowired
    IStudentSubjectService subjectService;
    @DeleteMapping("/subject/delete/{id}")
    public String deleteCourse(@PathVariable String id) throws Exception{
        subjectService.deleteSubject(id);
        return "subject deleted";
    }
}
