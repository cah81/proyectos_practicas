package com.formacion.BS7_2.student.infraestructure.dto.output;

import com.formacion.BS7_2.student.domain.Student;
import com.formacion.BS7_2.studentSubject.domain.StudentSubject;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * A DTO for the {@link Student} entity
 */

@Data
@NoArgsConstructor
public class StudentOutputDto implements Serializable {
    private  String id_student;
    private Integer id_person;
    private String id_teacher;
    private  int num_hours_week;
    private  String comments;
    private  String branch;
    private List<String> subjects = new ArrayList<>();

    public StudentOutputDto(Student student) {
        setId_student(student.getId_student());
        setId_person(student.getPerson().getId_person());
        setId_teacher(student.getTeacher().getId_teacher());
        setNum_hours_week(student.getNum_hours_week());
        setComments(student.getComments());
        setBranch(student.getBranch());
        setSubjects(student.getStudentSubjects().stream().map(StudentSubject::getId_subject).toList());
    }



}