package com.cabbookingsystem.service;

import com.cabbookingsystem.dto.DriverLoginRequest;
import com.cabbookingsystem.dto.DriverRegisterRequest;
import com.cabbookingsystem.entity.Driver;

public interface DriverService {
    String register(DriverRegisterRequest request);
    String login(DriverLoginRequest request);
    Driver getProfile(String jwtToken);
    void logout();
}