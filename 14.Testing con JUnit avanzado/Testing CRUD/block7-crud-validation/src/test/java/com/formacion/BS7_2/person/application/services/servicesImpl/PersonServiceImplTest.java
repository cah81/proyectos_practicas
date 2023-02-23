package com.formacion.BS7_2.person.application.services.servicesImpl;


import com.formacion.BS7_2.exception.EntityNotFoundException;
import com.formacion.BS7_2.exception.UnprocessableEntityException;
import com.formacion.BS7_2.person.application.services.PersonService;
import com.formacion.BS7_2.person.domain.model.Person;
import com.formacion.BS7_2.person.infraestructure.dto.input.PersonInputDto;
import com.formacion.BS7_2.person.infraestructure.dto.output.PersonOutputDto;
import com.formacion.BS7_2.person.infraestructure.dto.repository.PersonDaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;



@ContextConfiguration(classes = {PersonServiceImpl.class})
@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Autowired
    private PersonServiceImpl personServiceImpl;

    @Mock
    private PersonDaoRepository personDaoRepository;

    @InjectMocks
    private PersonService personService = new PersonServiceImpl();

    PersonInputDto personInputDto = null;

    Person person = null;
    Person person2 = null;

    @BeforeEach
    void setUp() {
        personInputDto = new PersonInputDto(1, "cah119", "as123", "carlos",
                "angulo", "ca@bosonit.com", "ca@hotmail.com", "madrid",
                true, new Date(), "http://localhost:8080/ejemplo", null);

        person = new Person(personInputDto);

        person2 = new Person(personInputDto);
    }

    @Tag("CreatePerson")
    @DisplayName("Add User with all fields")
    @Test
    void addUser() throws Exception {

        //give

        //when
        when(personDaoRepository.save(person)).thenReturn(person);
        PersonOutputDto personOutputDto = personService.addUser(personInputDto);
        verify(personDaoRepository).save(person);
        //then
        assertThat(personOutputDto).isEqualTo(new PersonOutputDto(person));
        assertThat(personOutputDto).isNotNull();


    }

    @Tag("usernameCannotBeNull")
    @DisplayName("Add User with Error fields")
    @Test
    void addUserWithExceptions() {

        //give
        personInputDto.setUsername(null);

        //when
        assertThrows(EntityNotFoundException.class, () -> personService.addUser(personInputDto));

        //then
        verify(personDaoRepository, never()).save(person);
    }

    @Tag("usernameCannotBeMoreThan10character")
    @DisplayName("Username cannot be more than 10 character")
    @Test
    void addUserCannotBeMoreThan10CharactersTest() {
        //give
        personInputDto.setUsername("testusernameincorrecto");
        //when
        assertThrows(UnprocessableEntityException.class, () -> personService.addUser(personInputDto));
        //then
        verify(personDaoRepository, never()).save(person);
    }


    @Tag("usernameCannotBeLessThan10character")
    @DisplayName("Username cannot be more than 10 character")
    @Test
    void addUserCannotBeLessThan6CharactersTest() {
        //give
        personInputDto.setUsername("cah");
        //when
        assertThrows(UnprocessableEntityException.class, () -> personService.addUser(personInputDto));
        //then
        verify(personDaoRepository, never()).save(person);
    }

    @Tag("PasswordCannotBeEmptyTest")
    @DisplayName("Password Cannot Be EmptyTest")
    @Test
    void addUserPasswordCannotBeEmptyTest() {
        //give
        personInputDto.setPassword(null);
        //when
        assertThrows(UnprocessableEntityException.class, () -> personService.addUser(personInputDto));
        //then
        verify(personDaoRepository, never()).save(person);
    }

    @Tag("NameCannotBeNull")
    @DisplayName("Name cannot be null")
    @Test
    void addUserNameCannotBeNullTest() {
        //give
        personInputDto.setName(null);
        //when
        assertThrows(UnprocessableEntityException.class, () -> personService.addUser(personInputDto));
        //then
        verify(personDaoRepository, never()).save(person);
    }

    @Tag("companyEmailCannotBeNull")
    @DisplayName("companyEmail cannot be null")
    @Test
    void addUserCompanyEmailCannotBeNullTest() {
        //give
        personInputDto.setCompany_email(null);
        //when
        assertThrows(UnprocessableEntityException.class, () -> personService.addUser(personInputDto));
        //then
        verify(personDaoRepository, never()).save(person);
    }

    @Tag("wrongEmailFormat")
    @DisplayName("wrong email format")
    @Test
    void addUserWrongEmailFormatTest() {
        //give
        personInputDto.setCompany_email("cag1234");
        //when
        assertThrows(UnprocessableEntityException.class, () -> personService.addUser(personInputDto));
        //then
        verify(personDaoRepository, never()).save(person);
    }

    @Tag("wrongPersonalEmailFormat")
    @DisplayName("wrong personalEmail format")
    @Test
    void addUserWrongPersonalEmailFormatTest() {
        //give
        personInputDto.setPersonal_email("cag1234");
        //when
        assertThrows(UnprocessableEntityException.class, () -> personService.addUser(personInputDto));
        //then
        verify(personDaoRepository, never()).save(person);
    }


    @Tag("personalEmailCannotBeNull")
    @DisplayName("personalEmail cannot be null")
    @Test
    void addUserPersonalEmailCannotBeNullTest() {
        //give
        personInputDto.setPersonal_email(null);
        //when
        assertThrows(UnprocessableEntityException.class, () -> personService.addUser(personInputDto));
        //then
        verify(personDaoRepository, never()).save(person);
    }


    @Tag("fieldActiveCannotBeNull")
    @DisplayName("field active cannot be null")
    @Test
    void addUserFieldActiveCannotBeNullTest() {
        //give
        personInputDto.setActive(null);
        //when
        assertThrows(UnprocessableEntityException.class, () -> personService.addUser(personInputDto));
        //then
        verify(personDaoRepository, never()).save(person);
    }


    @Tag("cityCannotEmpty")
    @DisplayName("City Cannot Empty")
    @Test
    void addUserFieldCityCannotNullTest() {
        //give
        personInputDto.setCity(null);
        //when
        assertThrows(UnprocessableEntityException.class, () -> personService.addUser(personInputDto));
        //then
        verify(personDaoRepository, never()).save(person);
    }

    @Tag("findById")
    @DisplayName("findById")
    @Test
    void findById() throws Exception {

        //give
        person.setId_person(1);
        Optional<Person> ofResult = Optional.of(person);
        //when
        when(personDaoRepository.findById((Integer) any())).thenReturn(ofResult);
        PersonOutputDto personOutputDto = personService.findById(1);
        verify(personDaoRepository).findById(person.getId_person());
        //then

        assertTrue(personOutputDto.getActive());
        assertEquals("cah119", personOutputDto.getUsername());
        assertEquals("as123", personOutputDto.getPassword());
        assertEquals("carlos", personOutputDto.getName());
        assertEquals("angulo", personOutputDto.getSurname());
        assertEquals("ca@bosonit.com", personOutputDto.getCompany_email());
        assertEquals("ca@hotmail.com", personOutputDto.getPersonal_email());

    }


    @Tag("findByIdNotFound")
    @DisplayName("findByIdNotFound")
    @Test
    void findByIdWithExceptionTest() throws Exception {

        //give
        person.setId_person(1);

        //when
        assertThrows(EntityNotFoundException.class, () -> {
            personService.findById(2);
        });

        //then
        verify(personDaoRepository).findById((Integer) any());


    }


    @Tag("findByName")
    @DisplayName("Find by Name")
    @Test
    void findByName() throws Exception {

        //give
        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person);
        //when
        when(personDaoRepository.findByUsername((String) any())).thenReturn(personList);

        //then
        //espero una coincidencia
        assertEquals(1, personService.findByName("cah119").size());
        verify(personDaoRepository).findByUsername((String) any());

    }


    @Tag("findByNameWithUserNameNotFound")
    @DisplayName("Find by Name and Username Not Found")
    @Test
    void findByNameWhenUserNameNotFound() throws Exception {

        //when
        when(personDaoRepository.findByUsername((String) any())).thenThrow(new EntityNotFoundException(
                "Person does not exist", 404, new Date()));
        //then
        assertThrows(EntityNotFoundException.class, () -> personService.findByName("janedoe"));

        verify(personDaoRepository).findByUsername((String) any());

    }

    @Tag("findByNamePersonListNotFound")
    @DisplayName("Find by Name PersonListNotFound")
    @Test
    void findByNamePersonListTestNotFound() throws Exception {
        //when
        when(personDaoRepository.findByUsername((String) any())).thenReturn(new ArrayList<>());
        //then
        assertThrows(EntityNotFoundException.class, () -> personService.findByName("other"));
        verify(personDaoRepository).findByUsername((String) any());
    }


    @Tag("updatePerson")
    @DisplayName("Update Person")
    @Test
    void updateUserTest() throws Exception {
        //give
        Optional<Person> ofResult = Optional.of(person);
        //when
        when(personDaoRepository.save((Person) any())).thenReturn(person);
        when(personDaoRepository.findById((Integer) any())).thenReturn(ofResult);
        personInputDto.setId(1);
        personInputDto.setName("jose");
        PersonOutputDto personOutputDto = personService.updateUser(1, personInputDto);
        //then
        assertTrue(personOutputDto.getActive());
        assertEquals("jose", personOutputDto.getName());
        verify(personDaoRepository).save((Person) any());
        verify(personDaoRepository).findById((Integer) any());
    }

    @Tag("updatePersonIdNotFound")
    @DisplayName("Update Person id Not found")
    @Test
    void updateUserTestIdNotFound() throws Exception {
        //give
        int id = 2;
        given(personDaoRepository.findById(2)).willReturn(Optional.empty());
        personInputDto.setName("maria");
        //when
        assertThrows(EntityNotFoundException.class, () -> personService.updateUser(id, personInputDto));
        //then
        verify(personDaoRepository, never()).save(new Person(personInputDto));
    }


    @Tag("deletePerson")
    @DisplayName("delete person")
    @Test
    void deleteUser() throws Exception {
        Optional<Person> ofResult = Optional.of(person);
        doNothing().when(personDaoRepository).delete((Person) any());
        when(personDaoRepository.findById((Integer) any())).thenReturn(ofResult);
        assertEquals("Person deleted", personService.deleteUser(1));
        verify(personDaoRepository).findById((Integer) any());
        verify(personDaoRepository).delete((Person) any());

    }

    @Tag("deletePersonIdNotFound")
    @DisplayName("delete Cannot be Id not found")
    @Test
    void deleteUserWithException() throws Exception {
        //given
        int id = 2;
        //when
        given(personDaoRepository.findById(id)).willReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> personService.deleteUser(id));
        //then
        verify(personDaoRepository, never()).delete(person);
    }

    @Tag("PersonList")
    @DisplayName("Person List")
    @Test
    void findALlTest() throws Exception {
      personDaoRepository.save(person);
      personDaoRepository.save(person2);
     given(personDaoRepository.findAll()).willReturn(List.of(person,person2));

      //when
        List<PersonOutputDto> personList = personService.findALl();
        verify(personDaoRepository).findAll();
        //then
        assertThat(personList).isNotNull();
        assertThat(personList.size()).isEqualTo(2);
    }


}