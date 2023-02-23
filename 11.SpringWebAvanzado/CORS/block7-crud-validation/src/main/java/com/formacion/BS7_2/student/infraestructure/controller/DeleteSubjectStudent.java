package com.formacion.BS7_2.student.infraestructure.controller;

import com.formacion.BS7_2.student.application.IStudentService;
import com.formacion.BS7_2.student.infraestructure.dto.output.StudentOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/delete")
public class DeleteSubjectStudent {
    @Autowired
    IStudentService studentService;
    @DeleteMapping("/subject/{id}")
    public String deleteSubjectToStudent(@PathVariable String id, @RequestBody List<String> subjects) throws Exception {

        return studentService.deleteSubjectFromStudent(id, subjects);
    }
}
