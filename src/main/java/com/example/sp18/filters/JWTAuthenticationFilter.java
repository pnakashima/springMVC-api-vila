package com.example.sp18.filters;

import com.example.sp18.model.dao.UserSpringSecurity;
import com.example.sp18.model.transport.CredentialsDTO;
import com.example.sp18.model.transport.JwtDTO;
import com.example.sp18.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler()); // pra "consertar" o erro 403 Forbidden pra 401 Unauthorized
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            ServletInputStream stream = request.getInputStream();
            CredentialsDTO credentials = new ObjectMapper().readValue(stream, CredentialsDTO.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    credentials.getEmail(), credentials.getPassword(), new ArrayList<>());
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            System.out.println("Authenticated!");
            return authenticate;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        String email = ((UserSpringSecurity) authResult.getPrincipal()).getUsername();
        JwtDTO generateToken = jwtUtil.generateToken(email);
        System.out.println("Email (successfulAuthentication): " + email);

        response.addHeader("Authorization", generateToken.getFullToken());
        response.getWriter().append(new Gson().toJson(generateToken));
    }

    private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

        @Override
        public void onAuthenticationFailure(HttpServletRequest request,
                                            HttpServletResponse response,
                                            AuthenticationException e) throws IOException, ServletException {
            response.setStatus(401);
            response.setContentType("apllication/json");
            response.getWriter().append(json());
        }

        private String json() {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", " +
                    "\"status\": 401, " +
                    "\"error\": \"Não autorizado\", " +
                    "\"message\"; \"Email ou senha inválidos\", " +
                    "\" path\": \"/login\"}";
        }
    }
}
