package com.iuc.requests.conf;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@Import(AppConfiguration.class)
public class AppConfigurationTest {
}
