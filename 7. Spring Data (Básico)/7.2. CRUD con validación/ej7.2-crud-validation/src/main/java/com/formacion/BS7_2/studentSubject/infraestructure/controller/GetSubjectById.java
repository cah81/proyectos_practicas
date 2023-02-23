package com.formacion.BS7_2.studentSubject.infraestructure.controller;

import com.formacion.BS7_2.studentSubject.application.service.IStudentSubjectService;
import com.formacion.BS7_2.studentSubject.infraestructure.dto.output.SubjectOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class GetSubjectById {
    @Autowired
    IStudentSubjectService subjectService;
    @GetMapping("subject/{id}")
    public List<SubjectOutputDto> showById(@PathVariable String id) throws Exception{
        return subjectService.getSubjectStudentById(id);
    }


}
