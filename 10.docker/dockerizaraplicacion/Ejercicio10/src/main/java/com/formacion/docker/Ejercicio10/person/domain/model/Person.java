package com.formacion.docker.Ejercicio10.person.domain.model;
import com.formacion.docker.Ejercicio10.person.infraestructure.dto.input.PersonInputDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
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
    private String password;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String company_email;
    @Column
    private String personal_email;
    @Column
    private String city;
    @Column
    private Boolean active;

    @Column(name ="create_at")
    @Temporal(TemporalType.DATE)
    private Date created_date;
    private String image_url;
    private Date termination_date;

    //@OneToOne(mappedBy = "person")
    // private Student student;

    // @OneToOne(mappedBy = "person")
    // private Teacher teacher;




    public Person(PersonInputDto personInputDto){
        this.username = personInputDto.getUsername();
        this.password = personInputDto.getPassword();
        this.name =personInputDto.getName();
        this.surname = personInputDto.getSurname();
        this.company_email= personInputDto.getCompany_email();
        this.personal_email= personInputDto.getPersonal_email();
        this.city = personInputDto.getCity();
        this.active = personInputDto.getActive();
        this.created_date = personInputDto.getCreated_date();
        this.image_url = personInputDto.getImage_url();
        this.termination_date= personInputDto.getTermination_date();
    }







    private static final long serialVersionUID = 1L;

}
