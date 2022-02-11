package com.iuc.requests.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iuc.requests.service.UserService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@Import(AppConfiguration.class)
@ComponentScan({"com.iuc.requests.service"})
public class AppConfigurationTest {

    @Bean
    public ObjectMapper objectMapper(){return  new ObjectMapper();}
}
