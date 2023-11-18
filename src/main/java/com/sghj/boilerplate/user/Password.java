package com.sghj.boilerplate.user;

import com.sghj.boilerplate.auth.support.PasswordEncoder;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {

    @Column(name = "password")
    private String value;

    private Password(final String value) {
        this.value = value;
    }

    public static Password encrypt(String password, PasswordEncoder encoder) {
        return new Password(encoder.encrypt(password));
    }

    public void validateIsSame(final Password password) {
        if(isNotSame(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    private boolean isNotSame(final Password password) {
        return !value.equals(password.getValue());
    }

    public String getValue() {
        return value;
    }
}
