package com.formacion.BS7_2.person.infraestructure.repository;

import com.formacion.BS7_2.person.domain.model.Person;
import com.formacion.BS7_2.person.infraestructure.dto.input.PersonInputDto;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.formacion.BS7_2.person.infraestructure.dto.repository.PersonDaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Date;
import java.util.List;


@DataJpaTest
class PersonDaoRepositoryTest {

    @Autowired
    PersonDaoRepository repositoryUnderTest;

    @BeforeEach
    void setUp() {

    }
    @Tag("findByUserNameCase1UserNameExist")
    @DisplayName("Find By UserName and this exist")
    @Test
    void findByUsername() {

        //give
        Person person = new Person();
        person.setId_person(1);
        person.setUsername("cah119");
        person.setPassword("as123");
        person.setCompany_email("ca@bosonit.com");
        person.setPersonal_email("ca@hotmail.com");
        person.setCity("madrid");
        person.setCreated_date(null);
        person.setTermination_date(null);
        person.setImage_url("http://example.com/image");
        repositoryUnderTest.save(person);

        //when
        Person personUserName = repositoryUnderTest.findByUsername("cah119");
        //then
       assertThat(personUserName.getUsername()).isEqualTo("cah119");

    }
    @Tag("findByUserNameCase2UserNameDoesNotExist")
    @DisplayName("Find By UserName and this does not exist")
    @Test
    void findByUsernameWhenDoesNotExistUserName() {

        //give
        Person person2 = new Person();
        person2.setId_person(1);
        person2.setUsername("cah2020");
        person2.setPassword("as123");
        person2.setCompany_email("ca@bosonit.com");
        person2.setPersonal_email("ca@hotmail.com");
        person2.setCity("madrid");
        person2.setCreated_date(null);
        person2.setTermination_date(null);
        person2.setImage_url("http://example.com/image");
        repositoryUnderTest.save(person2);

        //when
        repositoryUnderTest.findByUsername("cah119");
        //then
        assertNull(repositoryUnderTest.findByUsername("other"));

    }
}