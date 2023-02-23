package com.formacion.bs13_3;

import com.formacion.bs13_3.application.storageService.FileStorageService;
import com.formacion.bs13_3.infraestructure.configuration.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class UploadDownloadFileApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(UploadDownloadFileApplication.class, args);
	}

	@Autowired
	FileStorageService fileStorageService;


	@Override
	public void run(String... args) throws Exception {

	}
	@Bean
	CommandLineRunner init(){
		return (args)-> {
			if (args.length > 0)
				fileStorageService.setLocation(args[0]);
			else
			fileStorageService.setLocation("c:/temp/upload");
			fileStorageService.deleteAll();
			fileStorageService.init();
		};
	}
}



