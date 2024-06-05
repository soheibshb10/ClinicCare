package org.example.cliniccare1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cliniccare1.model.Sex;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterUserDto {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date birthDate;
    private Sex sex;
    private String address;
//    private String image;
    private String positionHeld;
    private String speciality;

}
