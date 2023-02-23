package com.formacion.BS7_2.studentSubject.infraestructure.dto.input;



import com.formacion.BS7_2.studentSubject.domain.StudentSubject;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link StudentSubject} entity
 */
@Data

public class SubjectInputDto {

    private String id_teacher;
    private String nameSubject;
    private String comments;
    private  Date initial_date;
    private  Date finish_date;
    private List<String> ids_students =new ArrayList<>();

/*
    public StudentSubject transformIntoStudentSubject(Teacher teacher){
        StudentSubject newStudentSubject = new StudentSubject();
        newStudentSubject.setTeacher(teacher);
        newStudentSubject.setNameSubject(this.nameSubject);
        newStudentSubject.setComments(this.comments);
        newStudentSubject.setInitial_date(this.initial_date);
        newStudentSubject.setFinish_date(this.finish_date);
        return newStudentSubject;
    }*/



    }
