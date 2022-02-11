package com.iuc.requests.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iuc.requests.controller.UserController;
import com.iuc.requests.dao.User;
import com.iuc.requests.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }




}
