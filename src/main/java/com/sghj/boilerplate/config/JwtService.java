package com.sghj.boilerplate.config;

import com.sghj.boilerplate.auth.JwtType;
import com.sghj.boilerplate.auth.vo.UserClaims;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Slf4j
@Component
public class JwtService {

    private static final String USER_EMAIL = "email";
    private static final String USER_NAME = "username";
    private static final String SECRET_KEY = "dGhpcyBzZWNyZXQga2V5IGlzIGVuY29kZWQgdGV4dCBieSBiYXNlNjQgYWxnb3JpdGht";

    private SecretKey key;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(SECRET_KEY);
        key = Keys.hmacShaKeyFor(bytes);
    }

    public String generateToken(UserClaims userClaims, JwtType type) {
        long now = System.currentTimeMillis();
        return Jwts
                .builder()
                .claim(USER_EMAIL, userClaims.email())
                .claim(USER_NAME, userClaims.username())
                .subject(userClaims.userId().toString())
                .issuedAt(new Date(now))
                .expiration(new Date(now + type.getExpireTime()))
                .signWith(key)
                .compact();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Claims extractAllClaims(final String jwt) {
        try {
            return Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload();
        } catch (UnsupportedJwtException ex) {
            log.info("[JwtService] UnsupportedJwtException");
        } catch (JwtException ex) {
            log.info("[JwtService] JwtException");
        } catch (IllegalArgumentException ex) {
            log.info("[JwtService] IllegalArgumentException");
        } catch (Exception ex) {
            log.info("[JwtService] Exception");
        }
        throw new IllegalArgumentException("Invalid Token");
    }
}
