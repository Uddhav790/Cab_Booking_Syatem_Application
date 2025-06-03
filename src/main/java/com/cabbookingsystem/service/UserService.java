package com.cabbookingsystem.service;

import com.cabbookingsystem.dto.UserLoginRequest;
import com.cabbookingsystem.dto.UserRegisterRequest;
import com.cabbookingsystem.entity.User;

public interface UserService {
    String register(UserRegisterRequest request);
    String login(UserLoginRequest request);
    User getProfile(String token);
    void logout();
}