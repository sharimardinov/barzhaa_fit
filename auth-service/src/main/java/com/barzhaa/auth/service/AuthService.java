package com.barzhaa.auth.service;


import com.barzhaa.auth.model.Role;
import com.barzhaa.auth.model.Status;
import com.barzhaa.auth.model.User;
import com.barzhaa.auth.repository.UserRepository;
import com.barzhaa.auth.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(String email, String password, String fullName, String phone) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email уже зарегистрирован");
        }

        Optional<User> existingPhoneUser = userRepository.findByPhone(phone);
        if (existingPhoneUser.isPresent()) {
            throw new RuntimeException("Номер телефона уже зарегистрирован");
        }

        User user = User.builder()
                .email(email)
                .passwordHash(passwordEncoder.encode(password))
                .fullName(fullName)
                .phone(phone)
                .role(Role.CLIENT)
                .status(Status.ACTIVE)
                .build();
        userRepository.save(user);

        return jwtService.generateToken(email);
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Неверные данные"));

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new RuntimeException("Неверный пароль");
        }

        return jwtService.generateToken(email);
    }
}
