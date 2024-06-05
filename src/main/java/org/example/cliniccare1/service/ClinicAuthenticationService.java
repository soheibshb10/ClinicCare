package org.example.cliniccare1.service;


import org.example.cliniccare1.dto.LoginClinicDto;
import org.example.cliniccare1.dto.RegisterClinicDto;
import org.example.cliniccare1.model.Clinic;
import org.example.cliniccare1.model.SecurityUser;
import org.example.cliniccare1.repository.ClinicRepository;
import org.example.cliniccare1.repository.SecurityUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class ClinicAuthenticationService {
    private final ClinicRepository clinicRepository;
    private final SecurityUserRepository securityUserRepository;

    private final ModelMapper modelMapper;
    private final ClinicService clinicService;
    private final AuthenticationManager authenticationManager;


    @Autowired
    public ClinicAuthenticationService(ClinicRepository clinicRepository, SecurityUserRepository securityUserRepository, AuthenticationManager authenticationManager, ModelMapper modelMapper, ClinicService clinicService, AuthenticationManager authenticationManager1) {
        this.clinicRepository = clinicRepository;
        this.securityUserRepository = securityUserRepository;
        this.modelMapper = modelMapper;
        this.clinicService = clinicService;
        this.authenticationManager = authenticationManager1;
    }

    public Clinic signup(RegisterClinicDto input) {

        // Map RegisterClinicDto to Clinic entity
        Clinic clinic = modelMapper.map(input, Clinic.class);
        return clinicService.saveClinic(clinic);

    }

    public Optional<SecurityUser> authenticate(LoginClinicDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        return securityUserRepository.findByEmail(input.getEmail());
    }



}
