package com.cabbookingsystem.entity;

import com.cabbookingsystem.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverID;

    private String name;
    
    @Column(unique = true, nullable = false)
    private String phone;
    private String licenseNumber;
    private String vehicleDetails;
    private String status; 
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private Role role;
}