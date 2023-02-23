package com.formacion.BS7_2.studentSubject.application.service;

import com.formacion.BS7_2.studentSubject.infraestructure.dto.input.SubjectInputDto;
import com.formacion.BS7_2.studentSubject.infraestructure.dto.output.SubjectOutputDto;

import java.util.List;

public interface IStudentSubjectService {

    //para agregar  curso
    public SubjectOutputDto addSubject(SubjectInputDto subjectInputDto) throws Exception;

    public SubjectOutputDto findSubjectById(String id);

    //para actualizar curso
    public SubjectOutputDto updateSubject(String id, SubjectInputDto subjectInputDto) throws Exception;
    //para eliminar curso
    public String deleteSubject(String id) throws Exception;
    //para mostrar todos los cursos
    public List<SubjectOutputDto>  getSubjectStudentById(String idStudent) ;

    public List<SubjectOutputDto> findALl();




}
