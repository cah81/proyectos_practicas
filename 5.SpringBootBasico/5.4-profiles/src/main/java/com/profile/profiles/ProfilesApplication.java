package com.profile.profiles;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@SpringBootApplication

public class ProfilesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfilesApplication.class, args);
	}
}
@Component
class MyProfiles implements CommandLineRunner{
	@Autowired
	private Environment environment;
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Active profiles: " +
				Arrays.toString(environment.getActiveProfiles()));
	}
}


