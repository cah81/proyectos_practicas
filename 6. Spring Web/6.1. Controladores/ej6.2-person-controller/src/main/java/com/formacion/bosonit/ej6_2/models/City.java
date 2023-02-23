package com.formacion.bosonit.ej6_2.models;

import org.springframework.stereotype.Component;

import java.io.Serializable;


public class City implements Serializable {
    String name;
    int nPopulation;



    public City(String name, int nPopulation) {
        this.name = name;
        this.nPopulation = nPopulation;
    }



    public int getnPopulation() {
        return nPopulation;
    }

    public void setnPopulation(int nPopulation) {
        this.nPopulation = nPopulation;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
