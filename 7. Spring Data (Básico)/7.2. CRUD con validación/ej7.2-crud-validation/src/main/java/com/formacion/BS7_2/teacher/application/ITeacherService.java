package com.formacion.BS7_2.teacher.application;

import com.formacion.BS7_2.person.domain.model.Person;
import com.formacion.BS7_2.teacher.domain.model.Teacher;
import com.formacion.BS7_2.teacher.infraestructure.dto.input.TeacherInputDto;
import com.formacion.BS7_2.teacher.infraestructure.dto.output.TeacherOutputDto;
import com.formacion.BS7_2.teacher.infraestructure.dto.output.TeacherPersonOutputDto;

import java.util.List;
import java.util.Optional;

public interface ITeacherService {
    //para agregar Teacher
    public TeacherOutputDto addTeacher(TeacherInputDto teacherInputDto) throws Exception;

    public TeacherOutputDto findById(String id)throws Exception;

    //para actualizar teacher
    public TeacherOutputDto updateTeacher(String id, TeacherInputDto teacherInputDto) throws Exception;
    //para eliminar teacher
    public String deleteTeacher(String id) throws Exception;
    //para mostrar todos los usuarios
    public List<TeacherOutputDto> findALl();
    public TeacherPersonOutputDto GetTeacherPerson(String id);

   public Optional<Teacher> getTeacherOptionalByPerson(Person person);
}
