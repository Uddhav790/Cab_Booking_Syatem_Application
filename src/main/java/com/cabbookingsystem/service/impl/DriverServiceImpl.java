package com.cabbookingsystem.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.cabbookingsystem.enums.Role;
import com.cabbookingsystem.dto.DriverLoginRequest;
import com.cabbookingsystem.dto.DriverRegisterRequest;
import com.cabbookingsystem.entity.Driver;
import com.cabbookingsystem.repository.DriverRepository;
import com.cabbookingsystem.service.DriverService;
import com.cabbookingsystem.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public String register(DriverRegisterRequest request) {
        Driver driver = Driver.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .licenseNumber(request.getLicenseNumber())
                .vehicleDetails(request.getVehicleDetails())
                .status("available")
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(Role.DRIVER)
                .build();
        driverRepository.save(driver);
        return "Driver registered successfully";
    }

    @Override
    public String login(DriverLoginRequest request) {
        Driver driver = driverRepository.findByPhone(request.getPhone())
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        if (!passwordEncoder.matches(request.getPassword(), driver.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }
        return jwtUtil.generateToken(driver.getPhone(), driver.getRole());
    }

    @Override
    public Driver getProfile(String token) {
        String phone = jwtUtil.getUsernameFromToken(token);
        return driverRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
    }

    @Override
    public void logout() {
        System.out.println("User logged out successfully");
    }
}









