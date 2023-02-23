package com.formacion.BS7_2.student.application;

import com.formacion.BS7_2.student.domain.Student;
import com.formacion.BS7_2.student.infraestructure.dto.input.StudentInputDto;
import com.formacion.BS7_2.student.infraestructure.dto.output.StudentOutputDto;


import java.util.List;


public interface IStudentService {

    //para agregar student
    public StudentOutputDto addStudent(StudentInputDto studentInputDto) throws Exception;

    public StudentOutputDto findById(String id)throws Exception;

  ///  public Student  findStudentById(String id) throws Exception;

    //para actualizar usuario
    public StudentOutputDto updateStudent(String id, StudentInputDto studentInputDto) throws Exception;
    //para eliminar usuario
    public void deleteStudent(String id) throws Exception;
    //para mostrar todos los usuarios
    public List<StudentOutputDto> findALl();


    //public StudentPersonOutputDto getStudentPerson(String id);

    public Student getStudentId(String id);

   public String addSubjectFromStudent(String id, List<String> ids_asig);

    public String deleteSubjectFromStudent(String idStudent, List<String> subjectIds);
}
