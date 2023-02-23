package com.bosonit.SBB1_Ej5_1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ApplicationSBB5_1 {
	public static void main(String[] args) {
		log.info("Iniciando la aplicacion");
		SpringApplication.run(ApplicationSBB5_1.class, args);
		log.info("Aplicacion finalizada");
	}

}
