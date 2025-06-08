package com.cabbookingsystem.dto;

import lombok.Data;

@Data
public class DriverRegisterRequest {
    private String name;
    private String phone;
    private String licenseNumber;
    private String vehicleDetails;
    private String password;
}