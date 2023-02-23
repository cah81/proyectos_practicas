package com.formacion.BS7_2.person.domain.model;


import com.formacion.BS7_2.person.infraestructure.dto.input.PersonInputDto;
import com.formacion.BS7_2.student.domain.Student;
import com.formacion.BS7_2.teacher.domain.model.Teacher;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "persons")

public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_person")
    private Integer id_person;
    @Column
    private String username;
    @Column
    private String passwd;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String emailcomp;
    @Column
    private String emailpers;
    @Column
    private String city;
    @Column
    private Boolean active;

    @Column(name ="create_at")
    @Temporal(TemporalType.DATE)
    private Date created_date;
    private String image_url;
    private Date termination_date;













    private static final long serialVersionUID = 1L;

}
