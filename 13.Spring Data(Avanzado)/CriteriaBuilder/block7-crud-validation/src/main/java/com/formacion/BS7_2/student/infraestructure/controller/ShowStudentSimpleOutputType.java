package com.formacion.BS7_2.student.infraestructure.controller;

import com.formacion.BS7_2.student.application.IStudentService;
import com.formacion.BS7_2.student.infraestructure.dto.output.StudentOutputDto;
import com.formacion.BS7_2.student.infraestructure.dto.output.StudentPersonOutputDto;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class ShowStudentSimpleOutputType {
    @Autowired
    IStudentService studentService;

    @GetMapping("/student/person/{id}")
    public StudentOutputDto showByNameStudentPersonId(@PathVariable String id, @RequestParam(name = "outputType", defaultValue = "simple") @NotNull String outputType) throws Exception {
        if (outputType.equals("full")) {
            return new StudentPersonOutputDto(studentService.getStudentId(id));
        } else {
            return new StudentOutputDto(studentService.getStudentId(id));
        }
    }
}
