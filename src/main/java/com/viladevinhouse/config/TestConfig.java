package com.viladevinhouse.config;

import com.viladevinhouse.controllers.service.EmailService;
import com.viladevinhouse.controllers.service.MockEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Bean
    public EmailService emailService() {
        return new MockEmailService("Test Sender");
    }
}
