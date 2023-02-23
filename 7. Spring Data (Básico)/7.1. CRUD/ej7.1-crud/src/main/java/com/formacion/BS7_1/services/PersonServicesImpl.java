package com.formacion.BS7_1.services;

import com.formacion.BS7_1.model.Person;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServicesImpl implements IPersonServices {
    @Autowired
    List<Person> personList;


    @Override
    public Person findById(Integer id) {
        return personList.stream().filter(person -> person.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Person findByName(String name) {
        return personList.stream().filter(person -> person.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Person addPerson(@Valid Person person) {
        person.setId(personList.size());
        personList.add(person);
        return person;
    }

    @Override
    public Person update( @Valid Person personUpdate,@Valid Person person) {
        personUpdate.setName(person.getName());
        personUpdate.setAge(person.getAge());
        personUpdate.setTown(person.getTown());

        return person;
    }

    @Override
    public Boolean removePerson(Integer id) {
        Person personSearch = findById(id);
        return personList.remove(personSearch);

    }
}
