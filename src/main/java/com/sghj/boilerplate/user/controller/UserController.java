package com.sghj.boilerplate.user.controller;

import com.sghj.boilerplate.auth.JwtAuthExtractor;
import com.sghj.boilerplate.config.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final JwtService jwtService;

    @GetMapping("/api/v1/hello")
    public ResponseEntity<String> hello(HttpServletRequest request) {
        extractAuthHeaderThenPrint(request);

        return ResponseEntity.ok("hello");
    }

    private void extractAuthHeaderThenPrint(final HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        String jwt = JwtAuthExtractor.extract(header);

        Claims claims = jwtService.extractAllClaims(jwt);
        String userId = jwtService.extractUserId(jwt);
        Date expireDate = jwtService.extractExpiration(jwt);
        String username = (String) claims.get("username");
        String email = (String) claims.get("email");

        log.info("userId = {}", userId);
        log.info("expireDt = {}", expireDate);
        log.info("username = {}", username);
        log.info("email = {}", email);
    }
}
