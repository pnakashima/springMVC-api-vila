package com.viladevinhouse.filters;

import com.viladevinhouse.controllers.service.ResidentService;
import com.viladevinhouse.util.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private JWTUtil jwtUtil;
    private ResidentService residentService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
                                  JWTUtil jwtUtil, ResidentService residentService) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.residentService = residentService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    null;
            try {
                usernamePasswordAuthenticationToken = getAuthentication(header.substring(7)); // substring(7) remove "Bearer ", ficando só o token
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (usernamePasswordAuthenticationToken != null) {
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) throws SQLException {
        if (jwtUtil.validToken(token)) {
            String email = jwtUtil.getEmailByToken(token);
            UserDetails user = residentService.loadUserByUsername(email);
            System.out.println("Email: " + email);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
            return usernamePasswordAuthenticationToken;
        }
        return null;
    }

}
