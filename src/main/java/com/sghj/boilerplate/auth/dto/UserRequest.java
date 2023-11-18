package com.sghj.boilerplate.auth.dto;

public class UserRequest {

    public record UserAddRequest(String username, String email, String password) {}

    public record UserLoginRequest(String email, String password) {}
}

