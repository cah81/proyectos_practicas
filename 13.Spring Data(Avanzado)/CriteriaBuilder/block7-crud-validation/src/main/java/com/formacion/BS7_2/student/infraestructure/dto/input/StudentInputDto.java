package com.formacion.BS7_2.student.infraestructure.dto.input;

import com.formacion.BS7_2.student.domain.Student;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link Student} entity
 */
@Data
public class StudentInputDto implements Serializable {

    private Integer id_person;
    private String id_teacher;
    private Integer num_hours_week;
    private String comments;
    private String branch;
    private String teacher;
    private List<String> subjects = new ArrayList<>();

    //para pasar informacion entrante a la entity
/*
    public StudentSubject transformStudentInputIntoStudent(Person person, Teacher teacher) {
        StudentSubject studentSubject = new StudentSubject();
        studentSubject.setPerson(person);
        studentSubject.setNum_hours_week(this.getNum_hours_week());
        studentSubject.setTeacher(teacher);
        studentSubject.setComments(this.getComments());
        studentSubject.setBranch(this.getBranch());
        return studentSubject;

    }*/
}
