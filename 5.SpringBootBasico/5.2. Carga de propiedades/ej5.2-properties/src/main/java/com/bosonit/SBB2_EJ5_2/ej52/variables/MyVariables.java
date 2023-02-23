package com.bosonit.SBB2_EJ5_2.ej52.variables;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class MyVariables implements CommandLineRunner {

    @Value("${greeting}")
    private String greeting;

    @Value("${my.number}")
    private int myNumber;

    @Value("${new.Property:Empty}")
    private String newProperty;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("El valor de Greeting  es : " + greeting);
        System.out.println("El valor de my.number es: " + myNumber);
        System.out.println("El valor de new.property es: " + newProperty);
    }
}
