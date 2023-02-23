package com.formacion.BS7_2.studentSubject.infraestructure.controller;

import com.formacion.BS7_2.studentSubject.application.service.IStudentSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("subjectList")
public class ShowListSubject {
    @Autowired
    IStudentSubjectService subjectService;



}
