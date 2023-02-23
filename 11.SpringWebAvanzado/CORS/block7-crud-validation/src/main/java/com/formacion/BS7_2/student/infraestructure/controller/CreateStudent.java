package com.formacion.BS7_2.student.infraestructure.controller;

import com.formacion.BS7_2.person.application.services.PersonService;
import com.formacion.BS7_2.student.application.IStudentService;
import com.formacion.BS7_2.student.infraestructure.dto.input.StudentInputDto;
import com.formacion.BS7_2.student.infraestructure.dto.output.StudentOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class CreateStudent {
    @Autowired
    IStudentService studentService;
    @Autowired
    PersonService personService;

    @PostMapping("/addstudent")
    public StudentOutputDto addStudent(@RequestBody StudentInputDto studentInputDto) throws Exception {
        return studentService.addStudent(studentInputDto);
    }

}
