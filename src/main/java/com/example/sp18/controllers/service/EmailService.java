package com.example.sp18.controllers.service;

import com.example.sp18.model.transport.ResidentDTO;
import org.springframework.mail.SimpleMailMessage;


public interface EmailService {

    void sendEmail(SimpleMailMessage message);
    void sendNewPassword(ResidentDTO user, String newPassword);

}
