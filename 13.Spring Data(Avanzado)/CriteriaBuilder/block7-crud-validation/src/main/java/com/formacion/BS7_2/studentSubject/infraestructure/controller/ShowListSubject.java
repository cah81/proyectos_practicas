package com.formacion.BS7_2.studentSubject.infraestructure.controller;

import com.formacion.BS7_2.studentSubject.application.service.IStudentSubjectService;
import com.formacion.BS7_2.studentSubject.domain.StudentSubject;
import com.formacion.BS7_2.studentSubject.infraestructure.dto.output.SubjectOutputDto;
import com.formacion.BS7_2.teacher.application.ITeacherService;
import com.formacion.BS7_2.teacher.infraestructure.dto.output.TeacherOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/subjectList")
public class ShowListSubject {
    @Autowired
    IStudentSubjectService subjectService;


    @GetMapping
    public List<SubjectOutputDto> findall(){
        return subjectService.findALl();
    }
}




