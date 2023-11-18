package com.sghj.boilerplate.auth.dto;

public record LoginResponse(String accessToken, String refreshToken) {
}
