package com.formacion.bosonit.ej6_2.models;


import org.springframework.stereotype.Component;

@Component
public class Person {
    String name;
    String city;
    Integer age;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


}
