package com.formacion.BS7_2.person.infraestructure.dto.output;

import com.formacion.BS7_2.person.domain.model.Person;
import com.formacion.BS7_2.role.domain.Role;
import com.formacion.BS7_2.role.infraestructure.dto.RoleOutputDto;
import lombok.Data;

import javax.persistence.CollectionTable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * A DTO for the {@link com.formacion.BS7_2.person.domain.model.Person} entity
 */
@Data
public class PersonOutputDto {
    private Integer id_person;
    private String username;

    private String name;

    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Boolean active;
    private  Date created_date;
    private  String image_url;
    private  Date termination_date;

    private Collection<Role> roles = new ArrayList<>();

    private Boolean admin;


    public PersonOutputDto(Person person) {

        this.id_person = person.getId_person();
        this.username = person.getUsername();
        this.name = person.getName();
        this.surname = person.getSurname();
        this.company_email = person.getCompany_email();
        this.personal_email = person.getPersonal_email();
        this.city = person.getCity();
        this.active = person.getActive();
        this.created_date = person.getCreated_date();
        this.image_url= person.getImage_url();
        this.termination_date = person.getTermination_date();
        this.roles = person.getRoles();
        this.admin = person.getAdmin();
    }


}


