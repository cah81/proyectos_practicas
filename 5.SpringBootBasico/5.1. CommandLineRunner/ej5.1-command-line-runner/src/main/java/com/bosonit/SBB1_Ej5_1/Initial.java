package com.bosonit.SBB1_Ej5_1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@SpringBootApplication
@Slf4j

public class Initial {
    @PostConstruct
    public void function1(){
        System.out.println("1.==========Hello From the Initial Class : Initial.==============");
    }

}
