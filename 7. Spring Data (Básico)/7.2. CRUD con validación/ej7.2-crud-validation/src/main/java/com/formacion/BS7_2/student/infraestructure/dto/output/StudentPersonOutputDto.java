package com.formacion.BS7_2.student.infraestructure.dto.output;

import com.formacion.BS7_2.student.domain.Student;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Student} entity
 */


@Data
@NoArgsConstructor
public class StudentPersonOutputDto extends StudentOutputDto implements Serializable {
    private Integer id_person;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String company_email;

    private String personal_email;
    private String city;
    private Boolean active;
    private Date created_date;
    private  String image_url;
    private  Date termination_date;

   // private String id_teacher;




    public StudentPersonOutputDto(Student student) {
        super(student);
        this.id_person = student.getPerson().getId_person();
        this.username= student.getPerson().getUsername();
        this.password = student.getPerson().getPassword();
        this.name = student.getPerson().getName();
        this.surname = student.getPerson().getSurname();
        this.company_email= student.getPerson().getCompany_email();
        this.personal_email = student.getPerson().getPersonal_email();
        this.city= student.getPerson().getCity();
        this.created_date= student.getPerson().getCreated_date();
        this.image_url= student.getPerson().getImage_url();
        this.termination_date = student.getPerson().getTermination_date();
        this.active = student.getPerson().getActive();
    }



}