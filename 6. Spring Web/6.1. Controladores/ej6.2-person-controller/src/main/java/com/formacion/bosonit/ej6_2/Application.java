package com.formacion.bosonit.ej6_2;

import com.formacion.bosonit.ej6_2.models.City;
import com.formacion.bosonit.ej6_2.models.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public List<City> loadCities(){
		List<City> cities = new ArrayList<City>();
		return cities;
	}

	@Bean("Bean1")
	public void createObjectPerson1(){
		Person person1 = new Person();
	}
	@Bean("Bean2")
	public void createObjectPerson2(){
		Person person2 = new Person();
	}
	@Bean("Bean3")
	public void createObjectPerson3(){
		Person person3 = new Person();
	}

}
