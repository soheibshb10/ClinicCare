package org.example.cliniccare1.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterClinicDto {
    private String name;
    private String email;
    private String password;
    private String type;
    private String description;
    private String place;
    private String address;
    private Date creationDate;
}
