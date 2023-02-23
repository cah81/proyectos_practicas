package com.formacion.BS7_2.person.infraestructure.dto.output;

import com.formacion.BS7_2.person.domain.model.Person;
import lombok.Data;
import java.util.Date;

/**
 * A DTO for the {@link com.formacion.BS7_2.person.domain.model.Person} entity
 */
@Data
public class PersonOutputDto {
    private Integer id_person;
    private String username;
    private String passwd;
    private String name;
    private String surname;
    private String emailcomp;
    private String emailpers;
    private String city;
    private Boolean active;
    private  Date created_date;
    private  String image_url;
    private  Date termination_date;


    public PersonOutputDto(Person person) {
        this.id_person = person.getId_person();
        this.username = person.getUsername();
        this.passwd =person.getPasswd();
        this.name = person.getName();
        this.surname = person.getSurname();
        this.emailcomp = person.getEmailcomp();
        this.emailpers = person.getEmailpers();
        this.city = person.getCity();
        this.active = person.getActive();
        this.created_date = person.getCreated_date();
        this.image_url= person.getImage_url();
        this.termination_date = person.getTermination_date();
    }


}


