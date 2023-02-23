package com.formacion.BS7_2.student.infraestructure.controller;

import com.formacion.BS7_2.student.application.IStudentService;
import com.formacion.BS7_2.student.infraestructure.dto.output.StudentOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class GetListStudent {
    @Autowired
    IStudentService studentService;
    @GetMapping("/studentlist")
    public List<StudentOutputDto> findall() {
        return studentService.findALl();
    }
}
