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

    public Person transformDtoToEntity(){
        Person person = new Person();
        person.setUsername(this.username);
        person.setPasswd(this.passwd);
        person.setName(this.name);
        person.setSurname(this.surname);
        person.setEmailcomp(this.emailcomp);
        person.setEmailpers(this.emailpers);
        person.setCity(this.city);
        person.setActive(this.active);
        person.setCreated_date(this.created_date);
        person.setImage_url(this.getImage_url());
        person.setTermination_date(this.termination_date);
        return person;
    }
    }



