package com.example.travel_diary.global.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final Long expiration;

    public JwtUtil(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") Long expiration) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.expiration = expiration;
    }


    // Token 생성
    public String createToken(String loginId) {
        String token = Jwts.builder()
                .subject(loginId)
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey)
                .compact();
        return token;
    }

    // Token에서 login id 추출
    public String getLoginIdFromToken(String token) {
        Claims payload = (Claims) Jwts.parser().verifyWith(secretKey).build().parse(token).getPayload();
        return payload.getSubject();
    }


    // Header에서 Token 값 추출
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    // token 이 만료 되지 않았는지?
    public boolean validateToken(String token) {
        Claims payload = (Claims) Jwts.parser().verifyWith(secretKey).build().parse(token).getPayload();
        return payload.getExpiration().after(new Date());
    }
}
