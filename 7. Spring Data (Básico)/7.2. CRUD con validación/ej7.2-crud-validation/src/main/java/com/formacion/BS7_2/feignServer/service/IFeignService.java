package com.formacion.BS7_2.feignServer.service;

import com.formacion.BS7_2.exception.EntityNotFoundException;
import com.formacion.BS7_2.feignServer.IFeignServer;
import com.formacion.BS7_2.teacher.domain.model.Teacher;
import com.formacion.BS7_2.teacher.infraestructure.dto.output.TeacherOutputDto;
import com.formacion.BS7_2.teacher.infraestructure.repository.TeacherDaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class IFeignService implements IFeignServer {

    private final IFeignServer IFeignServer;
    private final TeacherDaoRepository teacherDao;


    @Override
    public TeacherOutputDto getTeacher(String id_teacher) throws Exception {
        Teacher teacher = teacherDao.findById(id_teacher).orElseThrow(()->new EntityNotFoundException("DOes no exist",404,new Date()));
        return new TeacherOutputDto(teacher);
    }


}
