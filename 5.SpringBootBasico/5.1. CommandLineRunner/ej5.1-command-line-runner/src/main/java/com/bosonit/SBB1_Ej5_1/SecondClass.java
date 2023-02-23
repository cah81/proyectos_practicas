package com.bosonit.SBB1_Ej5_1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class SecondClass implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("2.=======Hello From the Second Class : SecondClass.=======");

    }
}
