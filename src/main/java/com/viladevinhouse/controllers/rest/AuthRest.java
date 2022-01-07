package com.viladevinhouse.controllers.rest;

import com.viladevinhouse.controllers.service.AuthService;
import com.viladevinhouse.controllers.service.ResidentService;
import com.viladevinhouse.model.dao.UserSpringSecurity;
import com.viladevinhouse.model.transport.JwtDTO;
import com.viladevinhouse.model.transport.MailDTO;
import com.viladevinhouse.util.JWTUtil;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/auth")
public class AuthRest {

    private JWTUtil jwtUtil;

    private AuthService authService;

    public AuthRest(JWTUtil jwtUtil, AuthService authService) {
        this.jwtUtil = jwtUtil;
        this.authService = authService;
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) throws IOException {
        UserSpringSecurity userSpringSecurity = ResidentService.authenticated();
        JwtDTO newToken = jwtUtil.generateToken(userSpringSecurity.getUsername());
        response.addHeader("Authorization", newToken.getFullToken());
        response.getWriter().append(new Gson().toJson(newToken));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/forgot")
    public ResponseEntity<Void> forgot(@RequestBody MailDTO mail) throws SQLException {
        authService.sendNewPass(mail.getEmail());
        return ResponseEntity.noContent().build();
    }

}
