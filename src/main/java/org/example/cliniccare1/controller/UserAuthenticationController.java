package org.example.cliniccare1.controller;



import org.example.cliniccare1.dto.LoginResponse;
import org.example.cliniccare1.dto.LoginUserDto;
import org.example.cliniccare1.dto.RegisterUserDto;
import org.example.cliniccare1.model.SecurityUser;
import org.example.cliniccare1.model.User;
import org.example.cliniccare1.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/auth")

public class UserAuthenticationController {
//    private final UserJwtService userjwtService;
    private final UserAuthenticationService userAuthenticationService;

    @Autowired
    public UserAuthenticationController( UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    @PostMapping("/signup")

    public ResponseEntity<String> register(@RequestBody RegisterUserDto registerUserDto) {
        System.out.println("user..."+registerUserDto);
        User registeredUser = userAuthenticationService.signup(registerUserDto);
        if (registeredUser != null) {
            String successMessage = "User successfully registered!";
            return ResponseEntity.ok(successMessage);
        } else {
            String errorMessage = "Failed to register User. Please check your input.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
//        if (loginUserDto == null || loginUserDto.getEmail() == null || loginUserDto.getPassword() == null)
//            return ResponseEntity.badRequest().body(new LoginResponse("Invalid login details"));
//
//        // Perform the authentication
//
//        SecurityUser authenticatedUser =  userAuthenticationService.authenticate(loginUserDto);
//        if (authenticatedUser == null)
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Authentication failed", 0));
//
//        // Generate the JWT token
////        String jwtToken = userjwtService.generateToken(authenticatedUser);
////        LoginResponse loginResponse = new LoginResponse(jwtToken, userjwtService.getExpirationTime());
//
//
//        return ResponseEntity.ok(loginResponse);
//    }




}
