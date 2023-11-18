package com.sghj.boilerplate.auth.service;

import com.sghj.boilerplate.auth.vo.UserClaims;
import com.sghj.boilerplate.auth.dto.LoginResponse;
import com.sghj.boilerplate.auth.dto.UserRequest;
import com.sghj.boilerplate.user.UserReader;
import com.sghj.boilerplate.config.JwtService;
import com.sghj.boilerplate.auth.support.PasswordEncoder;
import com.sghj.boilerplate.user.Password;
import com.sghj.boilerplate.user.User;
import com.sghj.boilerplate.user.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sghj.boilerplate.auth.JwtType.ACCESS;
import static com.sghj.boilerplate.auth.JwtType.REFRESH;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserReader userReader;
    private final UserJpaRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long join(final UserRequest.UserAddRequest userRequest) {
        Password password = Password.encrypt(userRequest.password(), passwordEncoder);

        User user = new User(userRequest.username(), userRequest.email(), password);
        User savedUser = userRepository.save(user);

        return savedUser.getId();
    }

    @Transactional
    public LoginResponse login(final UserRequest.UserLoginRequest userRequest) {
        Password password = Password.encrypt(userRequest.password(), passwordEncoder);
        User user = userReader.findByEmailAndPassword(userRequest.email(), password);
        user.validateIsSamePassword(password);

        UserClaims userClaims = new UserClaims(user.getId(), user.getEmail(), user.getUsername());
        String accessToken = jwtService.generateToken(userClaims, ACCESS);
        String refreshToken = jwtService.generateToken(userClaims, REFRESH);

        return new LoginResponse(accessToken, refreshToken);
    }
}
