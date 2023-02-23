package com.formacion.BS7_2.person.application.services;

import com.formacion.BS7_2.person.infraestructure.dto.input.PersonInputDto;
import com.formacion.BS7_2.person.infraestructure.dto.output.PersonOutputDto;
import java.util.List;


public interface PersonService {
    //para agregar usuario
    public PersonOutputDto addUser(PersonInputDto person) throws Exception;
    //para buscar por id
   // public PersonOutputDto findById(Integer id)  ;
    public PersonOutputDto findById(Integer id)throws Exception;
    //para buscar por nombre en la lista de usuarios creados
    public PersonOutputDto findByName(String username)throws Exception;
    //para actualizar usuario
    public PersonOutputDto updateUser(Integer id, PersonInputDto personInputDto) throws Exception;
    //para eliminar usuario
    public String deleteUser(Integer id) throws Exception;
    //para mostrar todos los usuarios
    public List<PersonOutputDto> findALl();




}
