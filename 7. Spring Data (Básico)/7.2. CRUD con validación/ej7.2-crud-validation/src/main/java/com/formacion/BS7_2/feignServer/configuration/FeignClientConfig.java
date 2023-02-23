package com.formacion.BS7_2.feignServer.configuration;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    @Bean
    Logger.Level feignLoggerLevel(){

        return  Logger.Level.FULL;
    }
}
