package com.formacion.BS7_2.teacher.application.impl;

import com.formacion.BS7_2.exception.EntityNotFoundException;
import com.formacion.BS7_2.exception.UnprocessableEntityException;
import com.formacion.BS7_2.person.domain.model.Person;
import com.formacion.BS7_2.person.infraestructure.repository.PersonDaoRepository;
import com.formacion.BS7_2.student.domain.Student;
import com.formacion.BS7_2.student.infraestructure.repository.StudentDaoRepository;
import com.formacion.BS7_2.teacher.application.ITeacherService;
import com.formacion.BS7_2.teacher.infraestructure.dto.output.TeacherOutputDto;
import com.formacion.BS7_2.teacher.infraestructure.dto.output.TeacherPersonOutputDto;
import com.formacion.BS7_2.teacher.infraestructure.repository.TeacherDaoRepository;
import com.formacion.BS7_2.teacher.domain.model.Teacher;
import com.formacion.BS7_2.teacher.infraestructure.dto.input.TeacherInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements ITeacherService {
    @Autowired
    PersonDaoRepository personDaoRepository;
    @Autowired
    TeacherDaoRepository teacherDaoRepository;

    @Autowired
    StudentDaoRepository studentDaoRepository;

    @Override
    public TeacherOutputDto addTeacher(TeacherInputDto teacherInputDto) throws Exception {

     Person person = personDaoRepository.findById(teacherInputDto.getId_person())
             .orElseThrow(()->new EntityNotFoundException("Not person found",404,new Date()));
       Optional<Student> studentOptional = studentDaoRepository.findByPerson(person);
       Optional<Teacher> teacherOptional = teacherDaoRepository.findByPerson(person);
       if(teacherOptional.isPresent() || studentOptional.isPresent()){
           throw new UnprocessableEntityException("The person is already a student or teacher", 422,new Date());
       }
        Teacher t = new Teacher(teacherInputDto);
        t.setPerson(person);
      //  Teacher t = teacherInputDto.transformTeacherInputIntoEntity(personOptional.get());
        teacherDaoRepository.save(t);
        return new TeacherOutputDto(t);
    }

    @Override
    @Transactional(readOnly = true)
    public TeacherOutputDto findById(String id) throws Exception {
        return new TeacherOutputDto(teacherDaoRepository.findById(id).orElseThrow(()->new EntityNotFoundException("No Person found",404, new Date())));
    }

    @Override
    public TeacherOutputDto updateTeacher(String id, TeacherInputDto teacherInputDto) throws Exception {
        Optional<Teacher> teacherOptional =teacherDaoRepository.findById(id);
        Teacher teacher;
        if(teacherOptional.isEmpty()){
            throw new EntityNotFoundException("teacher does not exist",404, new Date());

        }
        Person person = personDaoRepository.findById(teacherInputDto.getId_person()).orElseThrow(()->new EntityNotFoundException("Person does not exist",404,new Date()));

        teacher = teacherOptional.get();
        teacher.setComments(teacherInputDto.getComments());
        teacher.setBranch(teacherInputDto.getBranch());
        teacher.setPerson(person);
        teacherDaoRepository.save(teacher);

        return  new TeacherOutputDto(teacher);
    }

    @Override
    public String deleteTeacher(String id) throws Exception {
       Teacher teacher = teacherDaoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("the id does not exist",404,new Date()));
       teacherDaoRepository.delete(teacher);
        return "teacher deleted";
    }


    @Override
    public TeacherPersonOutputDto GetTeacherPerson(String id) {
        return new TeacherPersonOutputDto(teacherDaoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "teacher not found",404,new Date())));
    }


    @Override
    public List<TeacherOutputDto> findALl() {
       List<TeacherOutputDto> teacherList = new ArrayList<>();
       teacherDaoRepository.findAll().forEach(teacher -> {
           TeacherOutputDto teacherOutputDto = new TeacherOutputDto(teacher);
           teacherList.add(teacherOutputDto);
       });
       return teacherList;
    }
    @Override
    public Optional<Teacher> getTeacherOptionalByPerson(Person person) {
        return teacherDaoRepository.findByPerson(person);
    }


    }



