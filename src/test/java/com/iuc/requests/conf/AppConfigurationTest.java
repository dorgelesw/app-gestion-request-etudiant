package com.iuc.requests.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AppConfiguration.class)
@ComponentScan({"com.iuc.requests.service"})
public class AppConfigurationTest {

    @Bean
    public ObjectMapper objectMapper(){return  new ObjectMapper();}
}

