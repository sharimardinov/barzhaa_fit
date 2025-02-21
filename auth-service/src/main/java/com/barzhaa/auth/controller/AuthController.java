package com.barzhaa.auth.controller;

import com.barzhaa.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String fullName,
                           @RequestParam String phone) {
        return authService.register(email, password, fullName, phone);
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        return authService.login(email, password);
    }
}
