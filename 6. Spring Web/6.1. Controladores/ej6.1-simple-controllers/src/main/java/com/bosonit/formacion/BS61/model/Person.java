package com.bosonit.formacion.BS61.model;

import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class Person {

    private String name;
    private String town;
    private Integer age;
}
