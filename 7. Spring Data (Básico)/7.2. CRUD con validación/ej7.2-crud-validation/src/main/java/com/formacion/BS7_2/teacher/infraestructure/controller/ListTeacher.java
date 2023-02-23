package com.formacion.BS7_2.teacher.infraestructure.controller;

import com.formacion.BS7_2.teacher.application.ITeacherService;
import com.formacion.BS7_2.teacher.infraestructure.dto.output.TeacherOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class ListTeacher {
    @Autowired
    ITeacherService teacherService;
    @GetMapping("/teacherlist")
    public List<TeacherOutputDto> findall(){
        return teacherService.findALl();
    }
}
