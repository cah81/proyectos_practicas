package com.formacion.bs13_2.application;

import com.formacion.bs13_2.domain.Person;
import com.formacion.bs13_2.infraestructure.dto.PersonInputDto;
import com.formacion.bs13_2.infraestructure.dto.PersonOutputDto;

import java.util.List;

public interface IPersonService {
    PersonOutputDto getPersonById(Integer id);

    PersonOutputDto createPerson(PersonInputDto personInputDto);

    PersonOutputDto updatePerson(PersonInputDto personInputDto,Integer id);

    List<PersonOutputDto> getAllPerson();

    List<PersonOutputDto> getAllPersonPage(int pageSize, int numPage);

    public String deletePerson(Integer id);
}
