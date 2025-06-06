package com.cabbookingsystem.service;

import com.MyModule.dto.DriverLoginRequest;
import com.MyModule.dto.DriverRegisterRequest;
import com.MyModule.entity.Driver;

public interface DriverService {
    String register(DriverRegisterRequest request);
    String login(DriverLoginRequest request);
    Driver getProfile(String jwtToken);
    void logout();
}