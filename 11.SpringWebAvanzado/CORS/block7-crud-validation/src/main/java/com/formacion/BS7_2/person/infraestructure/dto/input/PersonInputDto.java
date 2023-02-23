package com.formacion.BS7_2.person.infraestructure.dto.input;

import com.formacion.BS7_2.person.domain.model.Person;
import com.formacion.BS7_2.teacher.domain.model.Teacher;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link com.formacion.BS7_2.person.domain.model.Person} entity
 */
@Data
@NoArgsConstructor
public class PersonInputDto {
    private Integer id_person;
    private String username;
    private String passwd;
    private String name;
    private String surname;
    private String emailcomp;
    private String emailpers;
    private String city;
    private Boolean active;
    private Date created_date;
    private String image_url;
    private Date termination_date;
}


