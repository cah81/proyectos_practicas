package com.formacion.BS7_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;




@SpringBootApplication
@EnableFeignClients("com.formacion.BS7_2.feignServer")// Inform to Spring that we are going to use Feign
public class Bs72CRUDApplication {

	public static void main(String[] args) {
		SpringApplication.run(Bs72CRUDApplication.class, args);
	}

}
