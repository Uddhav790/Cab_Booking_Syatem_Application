package com.cabbookingsystem.dto;
import lombok.Data;

@Data
public class DriverLoginRequest {
    private String phone;
    private String password;
}