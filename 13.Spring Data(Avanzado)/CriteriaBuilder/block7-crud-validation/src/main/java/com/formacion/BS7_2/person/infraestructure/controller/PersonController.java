package com.formacion.BS7_2.person.infraestructure.controller;

import com.formacion.BS7_2.person.application.services.PersonService;
import com.formacion.BS7_2.person.criteria.PersonPage;
import com.formacion.BS7_2.person.criteria.SearchFilterPerson;
import com.formacion.BS7_2.person.infraestructure.dto.input.PersonInputDto;
import com.formacion.BS7_2.person.infraestructure.dto.output.PersonOutputDto;
import com.formacion.BS7_2.person.infraestructure.repository.PersonCriteriaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class PersonController {
    @Autowired
    PersonService personService;

    @Autowired
    PersonCriteriaDao personCriteriaDao;



    @CrossOrigin("*")
    @PostMapping("/addperson")
    public PersonOutputDto addPerson(@RequestBody PersonInputDto personInputDto) throws Exception {
        return personService.addUser(personInputDto);
    }
    @DeleteMapping("/delete/{id}")
    public String deletePerson(@PathVariable Integer id) throws Exception {
        personService.deleteUser(id);
        return "User deleted";
    }

    @GetMapping("/getall")
    public List<PersonOutputDto> findall(){
        return personService.findALl();
    }

    @GetMapping("/person/{id}")
    public PersonOutputDto showById(@PathVariable Integer id) throws Exception {
        return personService.findById(id);
    }
    @PutMapping("/update/{id}")
    public PersonOutputDto updatePerson(@PathVariable Integer id, @RequestBody PersonInputDto personInputDto) throws Exception {
        return personService.updateUser(id,personInputDto);
    }
    @GetMapping("/person/username/{username}")
    public List<PersonOutputDto> showByName(@PathVariable("username") String username) throws Exception {
        return personService.findByName(username);
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<PersonOutputDto>> getPersonWithCriteria(PersonPage personPage,SearchFilterPerson searchFilterPerson, @RequestParam(required = false) String username, @RequestParam(required = false) String name,
                                                                      @RequestParam(required = false) String surname, @RequestParam(required = false) Date creationDate,
                                                                      @RequestParam(required = false) String dateCriteria, @RequestParam(required = false) String sortBy,
                                                                      @RequestParam(required = false) Integer page){

        return new ResponseEntity<>(personService.getData(personPage,searchFilterPerson), HttpStatus.OK);

    }















    //  @GetMapping("/greaterThan/username")
 //   public List<PersonOutputDto> getPeopleGreaterThanUser(
 //                                                         @RequestParam (defaultValue = "none")String username,
   //                                                       @RequestParam(defaultValue = "none")String order){
    //    return personService.getCreatePeopleByUser(username, order);
   // }



}


