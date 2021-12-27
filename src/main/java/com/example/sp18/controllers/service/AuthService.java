package com.example.sp18.controllers.service;

import com.example.sp18.model.transport.ReportDTO;
import com.example.sp18.model.transport.ResidentDTO;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Random;

@Service
public class AuthService {

    private EmailService emailService;
    private PasswordEncoder passwordEncoder;
    private ResidentService residentService;

    public AuthService(EmailService emailService, PasswordEncoder passwordEncoder, ResidentService residentService) {
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.residentService = residentService;
    }

    public void sendNewPass(String email) throws UsernameNotFoundException, SQLException {
        ResidentDTO user = residentService.getResident(email);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        String newPassword = generatePassword();
        String encodedPass = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPass);
//        residentService.updateUser(user);
        emailService.sendNewPassword(user, newPassword);

    }

    private String generatePassword(){
        return new String(generatePassword(12));
    }


    private static char[] generatePassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[length];

        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));

        for(int i = 4; i< length ; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return password;
    }
}
