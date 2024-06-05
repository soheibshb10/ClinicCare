package org.example.cliniccare1.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cliniccare1.model.Sex;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    @JsonIgnore
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone_number;
    private Date birthDate;
    private Sex sex;
    private String address;
    private String image;
    private String positionHeld;
    private String speciality;

}
