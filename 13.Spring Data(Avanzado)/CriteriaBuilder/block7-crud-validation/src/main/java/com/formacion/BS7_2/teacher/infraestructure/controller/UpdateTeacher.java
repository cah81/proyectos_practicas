package com.formacion.BS7_2.teacher.infraestructure.controller;

import com.formacion.BS7_2.teacher.application.ITeacherService;
import com.formacion.BS7_2.teacher.infraestructure.dto.input.TeacherInputDto;
import com.formacion.BS7_2.teacher.infraestructure.dto.output.TeacherOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class UpdateTeacher {

    @Autowired
    ITeacherService teacherService;
    @PutMapping("/update/{id}")
    public TeacherOutputDto updateTeacher(@PathVariable String id, @RequestBody TeacherInputDto teacherInputDto) throws  Exception{
        return teacherService.updateTeacher(id,teacherInputDto);
    }
}
