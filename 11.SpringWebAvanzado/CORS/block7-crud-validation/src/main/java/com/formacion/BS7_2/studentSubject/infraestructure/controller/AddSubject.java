package com.formacion.BS7_2.studentSubject.infraestructure.controller;

import com.formacion.BS7_2.studentSubject.application.service.IStudentSubjectService;
import com.formacion.BS7_2.studentSubject.infraestructure.dto.input.SubjectInputDto;
import com.formacion.BS7_2.studentSubject.infraestructure.dto.output.SubjectOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subject")
public class AddSubject {
    @Autowired
    IStudentSubjectService subjectService;


    @PostMapping("/subject/add")
    public SubjectOutputDto addSubject(@RequestBody SubjectInputDto subjectInputDto) throws Exception{
        return subjectService.addSubject(subjectInputDto);
    }
}
