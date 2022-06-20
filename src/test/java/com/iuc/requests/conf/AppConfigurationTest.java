package com.iuc.requests.conf;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AppConfiguration.class)
@ComponentScan({"com.iuc.requests.service"})
@EnableAutoConfiguration
public class AppConfigurationTest {}
