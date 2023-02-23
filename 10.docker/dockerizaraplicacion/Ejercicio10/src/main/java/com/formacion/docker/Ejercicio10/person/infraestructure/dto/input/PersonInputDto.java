package com.formacion.docker.Ejercicio10.person.infraestructure.dto.input;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link com.formacion.docker.Ejercicio10.person.domain.model} entity
 */

@Data
@NoArgsConstructor
public class PersonInputDto implements Serializable {
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
    private String image_url;
    private Date termination_date;


    //pasar informacion entrante a entity
   /* public Person transformIntoPerson() {
        Person person = new Person();
        person.setId_person(this.getId_person());
        person.setUsername(this.getUsername());
        person.setPassword(this.getPassword());
        person.setName(this.getName());
        person.setSurname(this.getSurname());
        person.setCompany_email(this.getCompany_email());
        person.setPersonal_email(this.getPersonal_email());
        person.setCity(this.getCity());
        person.setActive(this.getActive());
        person.setCreated_date(this.getCreated_date());
        person.setImage_url(this.getImage_url());
        person.setTermination_date(this.getTermination_date());
        return person;


    }*/
}
