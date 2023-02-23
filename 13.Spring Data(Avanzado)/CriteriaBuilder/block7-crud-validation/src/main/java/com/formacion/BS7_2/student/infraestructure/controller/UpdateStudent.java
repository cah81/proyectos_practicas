package com.formacion.BS7_2.student.infraestructure.controller;

import com.formacion.BS7_2.person.application.services.PersonService;
import com.formacion.BS7_2.student.application.IStudentService;
import com.formacion.BS7_2.student.infraestructure.dto.input.StudentInputDto;
import com.formacion.BS7_2.student.infraestructure.dto.output.StudentOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class UpdateStudent {
    @Autowired
    IStudentService studentService;
    @Autowired
    PersonService personService;
    @PutMapping("/update/{id}")
    public StudentOutputDto updateStudent(@PathVariable String id, @RequestBody StudentInputDto studentInputDto) throws Exception {
        return studentService.updateStudent(id, studentInputDto);
    }
}
