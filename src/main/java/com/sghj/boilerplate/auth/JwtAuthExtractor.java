package com.sghj.boilerplate.auth;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JwtAuthExtractor {

    private static final String BEARER_PREFIX = "Bearer";

    public String extract(final String authHeader) {
        return authHeader.substring(BEARER_PREFIX.length()).trim();
    }
}
