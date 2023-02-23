package com.formacion.BS7_2.person.infraestructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.formacion.BS7_2.person.application.services.PersonService;
import com.formacion.BS7_2.person.domain.model.Person;
import com.formacion.BS7_2.person.infraestructure.dto.input.PersonInputDto;
import com.formacion.BS7_2.person.infraestructure.dto.output.PersonOutputDto;
import com.formacion.BS7_2.person.infraestructure.dto.repository.PersonDaoRepository;


import java.util.ArrayList;
import java.util.Date;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;


import static org.mockito.ArgumentMatchers.any;


import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ContextConfiguration(classes = {PersonController.class})
@ExtendWith(MockitoExtension.class)
@WebMvcTest
class PersonControllerTest {

    @InjectMocks
    private PersonController personController;


    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private PersonService personService;
    @MockBean
    private PersonDaoRepository personDaoRepository;


    Person person = null;
    Person person2 = null;
    PersonInputDto personInputDto = null;

    PersonOutputDto personOutputDto = null;


//    @BeforeEach
//    void setUp() {
//        personInputDto = new PersonInputDto(1, "cah119", "as123", "carlos",
//                "angulo", "ca@bosonit.com", "ca@hotmail.com", "madrid",
//                true, new Date(), "http://localhost:8080/ejemplo", null);
//
//        person = new Person(personInputDto);
//        person2 = new Person(personInputDto);
//        personOutputDto = new PersonOutputDto(person);
//
//    }

    @Test
    @Tag("shouldAddPersonTest")
    @DisplayName("shouldAddPersonTest")
    void shouldAddPersonTest() throws Exception {

        Mockito.when(personService.addUser(ArgumentMatchers.any())).thenReturn(new PersonOutputDto(person));
        String json = mapper.writeValueAsString(personInputDto);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/person/addPerson")
                .contentType(MediaType.APPLICATION_JSON).content(json);
        MockMvcBuilders.standaloneSetup(personController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.equalTo("as123")))
                .andExpect(MockMvcResultMatchers.jsonPath("name", Matchers.equalTo("carlos")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname", Matchers.equalTo("angulo")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.company_email", Matchers.equalTo("ca@bosonit.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.equalTo("cah119")))
                .andDo(print());


    }

    @Tag("List Person")
    @DisplayName("personListTest")
    @Test
    void personListTest() throws Exception {
        //give
        List<PersonOutputDto> personOutputDtoList = new ArrayList<>();
        Person person1 = new Person(personInputDto);
        person1.setId_person(1);
        person1.setName("jose");
        PersonOutputDto personOutputDto = new PersonOutputDto(person1);
        personOutputDtoList.add(personOutputDto);
        //when
        Mockito.when(personService.findALl()).thenReturn(personOutputDtoList);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person/personList");

        //then
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.equalTo("jose")))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)
                ));


    }




    @Tag("ShouldShowByIdTest")
    @DisplayName("ShouldShowByIdTest")
    @Test
    void ShouldShowByIdTest() throws Exception {
        Integer userId = 1;
        person.setId_person(1);
        PersonOutputDto ofResult = new PersonOutputDto(person);
        //
        when(personService.findById(userId)).thenReturn(ofResult);
        //when
        mockMvc.perform(get("/person/person/{id}", userId))
                .andExpect(status().isOk()).andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.equalTo("cah119")))
                .andDo(print());


    }
    @Tag("testDeletePerson")
    @DisplayName("testDeletePerson")
    @Test
    void testDeletePerson() throws Exception {
        when(personService.deleteUser((Integer) org.mockito.Mockito.any())).thenReturn("Delete User");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/person/delete/{id}", 1);
        MockMvcBuilders.standaloneSetup(personController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("User deleted"));
    }

    @Tag("testDeletePerson2")
    @DisplayName("testDeletePerson2")
    @Test
    void testDeletePerson2() throws Exception {
        when(personService.deleteUser((Integer) org.mockito.Mockito.any())).thenReturn("Delete User");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/person/delete/{id}", 1);
        MockMvcBuilders.standaloneSetup(personController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("User deleted"));
    }

//    @Tag("testShowByName")
//    @DisplayName("testShowByName")
//    @Test
//    void testShowByName() throws Exception {
//        when(personService.findByName((String) org.mockito.Mockito.any())).thenReturn(new ArrayList<>());
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person/person/username/{username}",
//                "cah119");
//        MockMvcBuilders.standaloneSetup(personController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content().string("[]"));
//    }

    @Tag("testUpdatePerson")
    @DisplayName("testUpdatePerson")
    @Test
    void testUpdatePerson() throws Exception {
        Integer id = 1;
        personInputDto.setId(id);
        when(personService.updateUser((Integer) any(), (PersonInputDto) org.mockito.Mockito.any()))
                .thenReturn(new PersonOutputDto(new Person()));

        String content = (new ObjectMapper()).writeValueAsString(personInputDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/person/update/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(personController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andDo(print());
    }



}