package org.example.cliniccare1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;

    private long expiresIn;


    public LoginResponse(String invalidLoginDetails) {
        this.token=invalidLoginDetails;
    }
}
