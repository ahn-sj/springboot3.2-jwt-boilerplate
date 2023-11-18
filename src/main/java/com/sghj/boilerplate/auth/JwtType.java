package com.sghj.boilerplate.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JwtType {
    ACCESS(1000 * 60 * 30),     // 30분
    REFRESH(1000 * 60 * 60 * 2); // 2시간

    private final int expireTime;

    public static int getExpireTime(JwtType type) {
        if(type.equals(ACCESS)) {
            return ACCESS.getExpireTime();
        }
        return REFRESH.getExpireTime();
    }
}
