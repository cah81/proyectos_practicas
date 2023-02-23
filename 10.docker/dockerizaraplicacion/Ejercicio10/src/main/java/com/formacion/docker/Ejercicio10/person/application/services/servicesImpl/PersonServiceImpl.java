package com.formacion.docker.Ejercicio10.person.application.services.servicesImpl;


import com.formacion.docker.Ejercicio10.exception.EntityNotFoundException;
import com.formacion.docker.Ejercicio10.exception.UnprocessableEntityException;
import com.formacion.docker.Ejercicio10.person.application.services.PersonService;
import com.formacion.docker.Ejercicio10.person.domain.model.Person;

import com.formacion.docker.Ejercicio10.person.infraestructure.dto.input.PersonInputDto;
import com.formacion.docker.Ejercicio10.person.infraestructure.dto.output.PersonOutputDto;
import com.formacion.docker.Ejercicio10.person.infraestructure.repository.PersonDaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {


    @Autowired
    PersonDaoRepository personDaoRepository;



    //Servicio para agregar persona
    @Override
    public PersonOutputDto addUser(PersonInputDto personInputDto) throws Exception {

        testFields(personInputDto);//revision de campos
        Person person = new Person(personInputDto); //se pasa el objeto recibido por el DTO a la entity se guarda en un objeto
        personDaoRepository.save(person);
        return new PersonOutputDto(person); //retorna con un dto que muestra el objeto creado
    }

    @Override
    @Transactional(readOnly = true)
    public PersonOutputDto findById(Integer id) throws Exception {

        return new PersonOutputDto(personDaoRepository.findById(id).orElseThrow(()->new EntityNotFoundException("No Person found",404, new Date())));

    }

    @Override
    public List<PersonOutputDto> findByName(String userName) throws Exception {
        List<Person> personList= personDaoRepository.findByUsername(userName);
        if(personList.isEmpty()){
            throw new EntityNotFoundException("Person does not exist",404, new Date());
        }
        return personList.stream().map(PersonOutputDto::new).collect(Collectors.toList());

    }

    @Override
    public PersonOutputDto updateUser(Integer id, PersonInputDto personInputDto) throws Exception {
        Optional<Person> pOptional = personDaoRepository.findById(id);
        Person person;
        if(pOptional.isEmpty()){
            throw new EntityNotFoundException("Person does not exist",404, new Date());
        }
        testFields(personInputDto);
        person = pOptional.get();
        person.setUsername(personInputDto.getUsername());
        person.setPassword(personInputDto.getPassword());
        person.setName(personInputDto.getName());
        person.setSurname(personInputDto.getSurname());
        person.setCompany_email(personInputDto.getCompany_email());
        person.setPersonal_email(personInputDto.getPersonal_email());
        person.setCity(personInputDto.getCity());
        person.setActive(personInputDto.getActive());
        person.setImage_url(personInputDto.getImage_url());
        personDaoRepository.save(person);
        return new PersonOutputDto(person);

    }

    @Override
    public String deleteUser(Integer id) throws Exception {
        Optional<Person> personOptional = personDaoRepository.findById(id);
        if (personOptional.isEmpty()){
            throw new EntityNotFoundException("Error person does not exist",404, new Date());
        }

        personDaoRepository.delete(personOptional.get());
        return "Person deleted";
    }

    @Override
    public List<PersonOutputDto> findALl() {
        List<PersonOutputDto> personListDtoOutPut = new ArrayList<>();
        personDaoRepository.findAll().forEach(person -> {
            PersonOutputDto personOutputDto = new PersonOutputDto(person);
            personListDtoOutPut.add(personOutputDto);
        });
        return personListDtoOutPut;
    }




    private static void testFields(PersonInputDto personInputDto){
        if(personInputDto.getUsername()==null){
            throw new EntityNotFoundException("Username cannot be null", 404,new Date());
        }
        if(personInputDto.getUsername().length()>10){
            throw new UnprocessableEntityException ("Username cannot be 10 character",422,new Date());
        }
        if(personInputDto.getUsername().length()<6){
            throw new UnprocessableEntityException("username cannot be less than 6 characters",422,new Date());
        }
        if(personInputDto.getPassword()==null){
            throw new UnprocessableEntityException("password cannot be empty",422,new Date());
        }
        if(personInputDto.getName()==null){
            throw new UnprocessableEntityException("Name cannot be null",422,new Date());
        }
        if (personInputDto.getCompany_email() == null) {
            throw new UnprocessableEntityException("Company email cannot be null",422,new Date());
        }
        if (!personInputDto.getCompany_email().contains("@")) {
            throw new UnprocessableEntityException("wrong email format",422,new Date());
        }
        if (personInputDto.getPersonal_email() == null) {
            throw new UnprocessableEntityException("personal email cannot be null",422,new Date());
        }
        if (!personInputDto.getPersonal_email().contains("@")) {
            throw new UnprocessableEntityException("wrong email format",422,new Date());
        }
        if ((personInputDto.getCity()==null)){
            throw new UnprocessableEntityException("field cannot be null",422,new Date());
        }
        if (personInputDto.getActive() == null) {
            throw new UnprocessableEntityException("Enter a value please",422,new Date());
        }
    }


    }

