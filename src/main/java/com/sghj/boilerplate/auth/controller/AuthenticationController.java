package com.sghj.boilerplate.auth.controller;

import com.sghj.boilerplate.auth.dto.LoginResponse;
import com.sghj.boilerplate.auth.service.AuthenticationService;
import com.sghj.boilerplate.auth.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/api/v1/join")
    public ResponseEntity<Long> join(@RequestBody UserRequest.UserAddRequest userRequest) {
        Long userId = authenticationService.join(userRequest);

        return ResponseEntity.ok().body(userId);
    }

    @PostMapping("/api/v1/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserRequest.UserLoginRequest userRequest) {
        return ResponseEntity.ok().body(authenticationService.login(userRequest));
    }
}
