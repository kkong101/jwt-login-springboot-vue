package main.demo.utilization;

import com.google.common.hash.Hashing;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import main.demo.domain.basement.type.TokenType;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtToken {

    private final String subject = "ROWAN";
    private final String secret = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

    public String generateToken(String id, String name, TokenType type) {
        return Jwts.builder()
                .setSubject(type.getSubject())
                .claim("id",id)
                .claim("name",name)
                .claim("type", type.getTokenType())
                .signWith(getKey(),SignatureAlgorithm.HS256)
                .setExpiration(new Date(System.currentTimeMillis() + type.getExpireTime()))
                .compact();
    }

    public Claims parse(String input) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(input)
                .getBody();
    }


    // for Refresh token
    public boolean compareTo(Date end) {
        Date start = new Date(end.getTime() - (1000L * 60 * 60 * 24 * 50));

        Date now = new Date(System.currentTimeMillis());

        return (now.after(start) && now.before(end));
    }

    public boolean isExpired(Date compared) {
        Date now = new Date(System.currentTimeMillis());
        return now.after(compared);
    }

    private Key getKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
    }

}
