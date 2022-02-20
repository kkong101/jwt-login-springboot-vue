package main.demo.utilization;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import main.demo.configuration.property.MyPasswordProperties;
import main.demo.domain.basement.type.TokenType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtToken {

    @Autowired
    private MyPasswordProperties secret;

    public String generateToken(String id, TokenType type) {
        return Jwts.builder()
                .setSubject(type.getSubject())
                .claim("id",id)
                .claim("type", type.getTokenType())
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .setExpiration(new Date(System.currentTimeMillis() + type.getExpireTime()))
                .compact();
    }

    private Key getKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret.getSecretKey()));
    }

}
