package com.example.sp18;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootApplication
public class Sp18Application {

    public static void main(String[] args) {
        SpringApplication.run(Sp18Application.class, args);
    }

//    @Bean
//    public MailSender getMailSender() {
//        return new JavaMailSenderImpl();
//    }

}
