package com.profile.profiles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:LOCAL.properties")
@Profile("local")
public class ProfileLocal implements CommandLineRunner{
    @Value("${environment}")
    private String environment;
    @Value("${bd.url}")
    private String bdUrl;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Environment: " + environment  + " bdUrl:" + bdUrl);

    }
}
