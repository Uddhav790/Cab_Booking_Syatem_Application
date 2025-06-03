package com.cabbookingsystem.service.impl;

import com.cabbookingsystem.dto.*;
import com.cabbookingsystem.entity.User;
import com.cabbookingsystem.enums.Role;
import com.cabbookingsystem.repository.UserRepository;
import com.cabbookingsystem.service.UserService;
import com.cabbookingsystem.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public String register(UserRegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .createdAt(LocalDateTime.now().toString())
                .build();
        userRepository.save(user);
        return "User registered successfully!";
    }

    @Override
    public String login(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }
        return jwtUtil.generateToken(user.getEmail(), user.getRole());
    }

        @Override
    public User getProfile(String token) {
        String email = jwtUtil.extractEmail(token); // Extract email from the token
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void logout() {
        // In a stateless application, logout can be handled by simply removing the token on the client side.
        // Here we can implement any additional logic if needed, like invalidating the token.
        // For now, we will just log the action.
        System.out.println("User logged out successfully");
    }
}