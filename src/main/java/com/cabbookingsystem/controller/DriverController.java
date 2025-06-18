package com.cabbookingsystem.controller;

import com.cabbookingsystem.dto.DriverRegisterRequest;
import com.cabbookingsystem.dto.DriverLoginRequest;
import com.cabbookingsystem.entity.Driver;
import com.cabbookingsystem.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody DriverRegisterRequest request) {
        return ResponseEntity.ok(driverService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody DriverLoginRequest request) {
        return ResponseEntity.ok(driverService.login(request));
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('DRIVER')")
    public ResponseEntity<Driver> getProfile(@RequestHeader("Authorization") String token) {
        String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;
        return ResponseEntity.ok(driverService.getProfile(jwtToken));
    }

    @GetMapping("/logout")
    @PreAuthorize("hasRole('DRIVER')")
    public ResponseEntity<String> logout() {
        driverService.logout();
        return ResponseEntity.ok("Logged out successfully");
    }
}