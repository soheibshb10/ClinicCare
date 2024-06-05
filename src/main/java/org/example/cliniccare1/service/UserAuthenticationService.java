package org.example.cliniccare1.service;



import org.example.cliniccare1.dto.LoginUserDto;
import org.example.cliniccare1.dto.RegisterUserDto;
import org.example.cliniccare1.model.SecurityUser;
import org.example.cliniccare1.model.User;
import org.example.cliniccare1.repository.ClinicRepository;
import org.example.cliniccare1.repository.SecurityUserRepository;
import org.example.cliniccare1.repository.UserRepository;
import org.example.cliniccare1.service.serviceImpl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UserAuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    private final SecurityUserRepository securityUserRepository;
    private final UserServiceImpl userService;

    @Autowired
    public UserAuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, ModelMapper modelMapper, SecurityUserRepository securityUserRepository, ClinicRepository clinicRepository, UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
        this.securityUserRepository = securityUserRepository;
        this.userService = userService;
    }
    public User signup(RegisterUserDto input) {
        System.out.println("Registred user....."+input);
        // Map RegisterUserDto to Clinic entity
        User user = modelMapper.map(input, User.class);
        System.out.println("user..." + user);
       return  userService.saveUserWithRole(user,"PATIENT");

    }



    public Optional<SecurityUser> authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return  securityUserRepository.findByEmail(input.getEmail());
    }




}
