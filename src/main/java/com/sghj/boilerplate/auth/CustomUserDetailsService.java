package com.sghj.boilerplate.auth;

import com.sghj.boilerplate.user.User;
import com.sghj.boilerplate.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserReader userReader;

    @Override
    public UserDetails loadUserByUsername(final String subject) throws UsernameNotFoundException {
        Long userId = Long.valueOf(subject);
        User user = userReader.findById(userId);

        return new CustomUserDetails(user);
    }
}
