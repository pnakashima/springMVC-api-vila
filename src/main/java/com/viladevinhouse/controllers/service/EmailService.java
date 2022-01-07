package com.viladevinhouse.controllers.service;

import com.viladevinhouse.model.transport.ResidentDTO;
import org.springframework.mail.SimpleMailMessage;


public interface EmailService {

    void sendEmail(SimpleMailMessage message);
    void sendNewPassword(ResidentDTO user, String newPassword);

}
