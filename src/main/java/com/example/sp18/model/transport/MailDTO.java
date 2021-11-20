package com.example.sp18.model.transport;

import org.springframework.lang.NonNull;

import java.io.Serializable;


public class MailDTO implements Serializable {

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
