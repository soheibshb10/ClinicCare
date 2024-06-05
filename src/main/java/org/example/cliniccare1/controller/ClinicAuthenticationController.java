package org.example.cliniccare1.controller;


import org.example.cliniccare1.dto.LoginResponse;
import org.example.cliniccare1.dto.RegisterClinicDto;
import org.example.cliniccare1.model.Clinic;
import org.example.cliniccare1.model.SecurityUser;
import org.example.cliniccare1.service.ClinicAuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clinic/auth")


public class ClinicAuthenticationController {

    private final ClinicAuthenticationService clinicAuthenticationService;

    public ClinicAuthenticationController( ClinicAuthenticationService clinicAuthenticationService) {
        this.clinicAuthenticationService = clinicAuthenticationService;
    }


    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody RegisterClinicDto registerClinicDto) {
        Clinic registeredClinic = clinicAuthenticationService.signup(registerClinicDto);

        if (registeredClinic != null) {
            String successMessage = "Clinic successfully registered!";
            return ResponseEntity.ok(successMessage);
        } else {
            String errorMessage = "Failed to register clinic. Please check your input.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginClinicDto loginClinicDto) {
//        if (loginClinicDto == null || loginClinicDto.getEmail() == null || loginClinicDto.getPassword() == null)
//            return ResponseEntity.badRequest().body(new LoginResponse("Invalid login details"));
//
//        SecurityUser authenticatedClinic =  clinicAuthenticationService.authenticate(loginClinicDto);
//        if (authenticatedClinic == null)
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Authentication failed"));
//
//        String jwtToken = jwtService.generateToken(authenticatedClinic);
//
//        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());
//
//
//        return ResponseEntity.ok(loginResponse);
//    }

}

