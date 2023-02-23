package com.formacion.BS7_2.teacher.infraestructure.controller;

import com.formacion.BS7_2.teacher.application.ITeacherService;
import com.formacion.BS7_2.teacher.infraestructure.dto.input.TeacherInputDto;
import com.formacion.BS7_2.teacher.infraestructure.dto.output.TeacherOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class CreateTeacher {
    @Autowired
    ITeacherService teacherService;
    @PostMapping("/addteacher")
    public TeacherOutputDto addTeacher(@RequestBody TeacherInputDto teacherInputDto) throws Exception{
        return teacherService.addTeacher(teacherInputDto);
    }
}
