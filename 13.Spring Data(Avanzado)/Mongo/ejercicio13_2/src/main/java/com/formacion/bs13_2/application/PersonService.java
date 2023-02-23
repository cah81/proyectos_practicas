package com.formacion.bs13_2.application;

import com.formacion.bs13_2.domain.Person;
import com.formacion.bs13_2.exception.EntityNotFoundException;
import com.formacion.bs13_2.exception.UnprocessableEntityException;
import com.formacion.bs13_2.generator.SequenceGeneratorService;
import com.formacion.bs13_2.infraestructure.dto.PersonInputDto;
import com.formacion.bs13_2.infraestructure.dto.PersonOutputDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.formacion.bs13_2.domain.Person.SEQUENCE_NAME;

@Service

public class PersonService implements IPersonService{

    @Autowired
    private SequenceGeneratorService service;

    private final MongoTemplate mongoTemplate;

    public PersonService( MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;

    }


    @Override
    public PersonOutputDto getPersonById(Integer id) {
       Person person = mongoTemplate.findById(id, Person.class);
       if(person==null) {
           throw new RuntimeException("Person does not  exist");
       }else{
           return convertPersonToPersonOutputDto(person);
       }

    }
    @Override
    public PersonOutputDto createPerson(PersonInputDto personInputDto) {
        testFields(personInputDto);
        Person person = convertPersonInputDtoToEntity(personInputDto);


        person.setId(service.getSequenceNumber(SEQUENCE_NAME));
        //asigno id, ya que no se genera automaticamente el id al objeto de la coleccion
        //en mongoTemplate no hace uso de repositorio en vista de que viene incluido
        //en la clase MongoTemplate
        mongoTemplate.save(person);
        return convertPersonToPersonOutputDto(person);
    }

    @Override
    public PersonOutputDto updatePerson(PersonInputDto personInputDto, Integer id) {
        Person person = mongoTemplate.findById(id, Person.class);
        if(person==null){
            throw  new RuntimeException("Person does not exist");
        }else{
            testFields(personInputDto);
            person.setUsuario(personInputDto.getUsuario());
            person.setPassword(personInputDto.getPassword());
            person.setName(personInputDto.getName());
            person.setSurname(personInputDto.getSurname());
            person.setCompany_email(personInputDto.getCompany_email());
            person.setPersonal_email(personInputDto.getPersonal_email());
            person.setCity(personInputDto.getCity());
            person.setCreated_date(personInputDto.getCreated_date());
            person.setActive(personInputDto.getActive());
            person.setImagen_url(personInputDto.getImagen_url());
            person.setTermination_date(personInputDto.getTermination_date());
            mongoTemplate.save(person);

        }
        return convertPersonToPersonOutputDto(person);
    }

    @Override
    public List<PersonOutputDto> getAllPerson() {
        return mongoTemplate.findAll(Person.class).stream().map(this::convertPersonToPersonOutputDto).toList();

       }

    @Override
    public List<PersonOutputDto> getAllPersonPage(int pageSize, int numPage) {
       Query query = new Query();
       query.skip(pageSize*numPage);
       query.limit(pageSize);
       return mongoTemplate.find(query, Person.class).stream().map(this::convertPersonToPersonOutputDto).toList();
    }

    @Override
    public String deletePerson(Integer id) {
        Person person = mongoTemplate.findById(id, Person.class);
        if(person==null){
            throw new RuntimeException("Person does not exist");
        }
        mongoTemplate.remove(person);
        return "Person Deleted";
    }


    private Person convertPersonInputDtoToEntity(PersonInputDto personInputDto){
        Person person = new Person();
        person.setUsuario(personInputDto.getUsuario());
        person.setPassword(personInputDto.getPassword());
        person.setName(personInputDto.getName());
        person.setSurname(personInputDto.getSurname());
        person.setCompany_email(personInputDto.getCompany_email());
        person.setPersonal_email(personInputDto.getPersonal_email());
        person.setCity(personInputDto.getCity());
        person.setCreated_date(personInputDto.getCreated_date());
        person.setActive(personInputDto.getActive());
        person.setImagen_url(personInputDto.getImagen_url());
        person.setTermination_date(personInputDto.getTermination_date());
        return person;
    }

    private PersonOutputDto convertPersonToPersonOutputDto(Person person){
        PersonOutputDto personOutputDto = new PersonOutputDto();
        personOutputDto.setId(person.getId());
        personOutputDto.setUsuario(person.getUsuario());
        personOutputDto.setPassword(person.getPassword());
        personOutputDto.setName(person.getName());
        personOutputDto.setSurname(person.getSurname());
        personOutputDto.setCompany_email(person.getCompany_email());
        personOutputDto.setPersonal_email(person.getPersonal_email());
        personOutputDto.setCity(person.getCity());
        personOutputDto.setActive(person.getActive());
        personOutputDto.setCreated_date(person.getCreated_date());
        personOutputDto.setImagen_url(person.getImagen_url());
        personOutputDto.setTermination_date(person.getTermination_date());

        return personOutputDto;

    }

    private static void testFields(PersonInputDto personInputDto){
        if(personInputDto.getUsuario()==null){
            throw new EntityNotFoundException("Username cannot be null", 404,new Date());
        }
        if(personInputDto.getUsuario().length()>10){
            throw new UnprocessableEntityException("Username cannot be 10 character",422,new Date());
        }
        if(personInputDto.getUsuario().length()<6){
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
