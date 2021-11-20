package com.example.sp18.config;

import com.example.sp18.controllers.service.EmailService;
import com.example.sp18.controllers.service.SmtpEmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
@Profile("dev")
public class DevConfig {

    private String sender;

    private MailSender mailSender;

    public DevConfig(@Value("${default.sender}") String sender, MailSender mailSender) {
        this.sender = sender;
        this.mailSender = mailSender;
    }

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService(sender, mailSender);
    }


}
