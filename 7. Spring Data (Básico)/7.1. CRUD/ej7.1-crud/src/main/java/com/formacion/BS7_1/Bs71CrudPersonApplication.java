package com.formacion.BS7_1;

import com.formacion.BS7_1.model.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Bs71CrudPersonApplication {

	public static void main(String[] args) {
		SpringApplication.run(Bs71CrudPersonApplication.class, args);
	}
	@Bean
	public List<Person> personRegister(){
		return new ArrayList<>();
	}

}
