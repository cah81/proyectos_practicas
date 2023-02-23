package com.bosonit.SBB_EJ5_3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ApplicationSBB5_3 {
	private static final Logger LOGGER=LoggerFactory.getLogger(ApplicationSBB5_3.class);

	public static void main(String[] args)
	{
		SpringApplication.run(ApplicationSBB5_3.class, args);
		LOGGER.info("simple log statement with inputs {},{} and {}",1 ,2,3);
		LOGGER.warn("This is a message of Warning");
		LOGGER.debug("Debug Message Logged !!!!");
		LOGGER.error("Error Message Logged !!!",new NullPointerException("Something is NULL"));
		LOGGER.trace("This a sample of message !!!!!");


	}






}
