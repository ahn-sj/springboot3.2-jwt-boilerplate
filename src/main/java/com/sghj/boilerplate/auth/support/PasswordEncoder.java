package com.sghj.boilerplate.auth.support;

public interface PasswordEncoder {
    String encrypt(String password);
}
