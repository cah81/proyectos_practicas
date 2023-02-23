package com.formacion.BS7_2.person.infraestructure.dto.input;

import com.formacion.BS7_2.person.domain.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sonatype.guice.plexus.config.Roles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * A DTO for the {@link com.formacion.BS7_2.person.domain.model.Person} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonInputDto implements Serializable {

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
    private Collection<Roles> roles = new ArrayList<>();
    private Boolean admin;



}
