package com.viladevinhouse.util;

import com.viladevinhouse.model.transport.JwtDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    private String secret;
    private long expiration;

    public JWTUtil(@Value("${jwt.secret}") String secret,
                   @Value("${jwt.expiration}") long expiration) {
        this.secret = secret;
        this.expiration = expiration;
    }

    public JwtDTO generateToken(String email) {
        Date tokenExpiration = new Date(System.currentTimeMillis() + expiration);
        String token = Jwts.builder()
                            .setSubject(email)
                            .setExpiration(tokenExpiration)
                            .signWith(SignatureAlgorithm.HS512, secret)
                            .compact();
        return new JwtDTO("Bearer", token, tokenExpiration.getTime());
    }

    public boolean validToken(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            String email = claims.getSubject();
            Date expiration = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            return email != null && expiration != null && now.before(expiration);
        }
        return false;
    }

    private Claims getClaims(String token) {
        try {
            Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return parseClaimsJws.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public String getEmailByToken(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }
}
