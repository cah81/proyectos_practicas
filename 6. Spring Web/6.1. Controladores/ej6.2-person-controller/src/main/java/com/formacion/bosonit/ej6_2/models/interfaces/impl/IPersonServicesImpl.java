package com.formacion.bosonit.ej6_2.models.interfaces.impl;

import com.formacion.bosonit.ej6_2.models.Person;
import com.formacion.bosonit.ej6_2.models.interfaces.IPersonService;

import org.springframework.stereotype.Service;

@Service
public class IPersonServicesImpl implements IPersonService {
    Person u = new Person();


    public Person miObject(){
        return u;
    }
}
