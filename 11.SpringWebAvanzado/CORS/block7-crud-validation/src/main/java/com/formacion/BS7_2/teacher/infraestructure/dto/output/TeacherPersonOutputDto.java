package com.formacion.BS7_2.teacher.infraestructure.dto.output;

import com.formacion.BS7_2.student.infraestructure.dto.output.StudentPersonOutputDto;
import com.formacion.BS7_2.teacher.domain.model.Teacher;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link Teacher} entity
 */
@Data
public class TeacherPersonOutputDto implements Serializable {


    private Integer id_person;
    private String username;
    private String name;
    private String surname;
    private String company_email;

    private String personal_email;
    private String city;
    private Boolean active;
    private Date created_date;
    private  String image_url;
    private  Date termination_date;

    private  String id_teacher;
    private final String comments;
    private final String branch;

    //private List<StudentPersonOutputDto> students;

    public TeacherPersonOutputDto(Teacher teacher){
        this.id_person = teacher.getPerson().getId_person();
        this.username = teacher.getPerson().getUsername();
        this.name = teacher.getPerson().getName();
        this.surname = teacher.getPerson().getSurname();
        this.company_email = teacher.getPerson().getEmailcomp();
        this.personal_email = teacher.getPerson().getEmailpers();
        this.city = teacher.getPerson().getCity();
        this.active = teacher.getPerson().getActive();
        this.created_date = teacher.getPerson().getCreated_date();
        this.image_url = teacher.getPerson().getImage_url();
        this.termination_date = teacher.getPerson().getTermination_date();
        this.id_teacher = teacher.getId_teacher();
        this.comments = teacher.getComments();
        this.branch = teacher.getBranch();

    }

}