package com.formacion.BS7_2.studentSubject.infraestructure.controller;

import com.formacion.BS7_2.studentSubject.application.service.IStudentSubjectService;
import com.formacion.BS7_2.studentSubject.infraestructure.dto.input.SubjectInputDto;
import com.formacion.BS7_2.studentSubject.infraestructure.dto.output.SubjectOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subject")
public class UpdateSubject {
    @Autowired
    IStudentSubjectService subjectService;
    @PutMapping("/update/{id}")
    public SubjectOutputDto updateCourse(@PathVariable String id, @RequestBody SubjectInputDto subjectInputDto) throws  Exception{
        return subjectService.updateSubject(id, subjectInputDto);
    }
}

