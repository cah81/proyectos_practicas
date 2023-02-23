package com.formacion.BS7_2.feignServer.controller;

import com.formacion.BS7_2.feignServer.IFeignServer;
import com.formacion.BS7_2.teacher.infraestructure.dto.output.TeacherOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class FeignController {


  private final IFeignServer feignServer;

    @GetMapping("/teacher/{id}")
    public TeacherOutputDto getTeacherById(@PathVariable("id") String idTeacher) throws Exception {
        return feignServer.getTeacher(idTeacher);
    }

}
