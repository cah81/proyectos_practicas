package com.formacion.BS7_2.studentSubject.infraestructure.dto.output;

import com.formacion.BS7_2.student.domain.Student;
import com.formacion.BS7_2.studentSubject.domain.StudentSubject;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A DTO for the {@link StudentSubject} entity
 */
@Data
public class SubjectOutputDto implements Serializable {
    private String id_subject;
    private String id_teacher;
    private String nameSubject;
    private String comments;
    private Date initial_date;
    private Date finish_date;
    private List<String> idsStudents = new ArrayList<>();


  public SubjectOutputDto(StudentSubject studentSubject){
      setId_teacher(studentSubject.getTeacher().getId_teacher());
     setId_subject(studentSubject.getId_subject());
     setNameSubject(studentSubject.getNameSubject());
     setComments(studentSubject.getComments());
     setInitial_date(studentSubject.getInitial_date());
     setFinish_date(studentSubject.getFinish_date());
     setIdsStudents(studentSubject.getStudents().stream().map(Student::getId_student).collect(Collectors.toList()));
  }











}

