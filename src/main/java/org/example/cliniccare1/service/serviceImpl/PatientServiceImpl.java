package org.example.cliniccare1.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.example.cliniccare1.model.Profile;
import org.example.cliniccare1.model.User;
import org.example.cliniccare1.repository.ProfileRepository;
import org.example.cliniccare1.repository.UserRepository;
import org.example.cliniccare1.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

@AllArgsConstructor
@Transactional

public class PatientServiceImpl implements PatientService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserServiceImpl userService;

    @Override
    public List<User> getALLPatients() {
        return userRepository.findUsersByProfileRole("PATIENT");
    }

    @Override
    public User savePatient(User user) {
        return userService.saveUserWithRole(user,"PATIENT");
    }

    @Override
    public Optional<User> getPayientById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updatePatient(User updatedPatient) {
        // Retrieve the existing user from the repository using the provided id
            return  userService.updateUserWithRole( updatedPatient, "PATIENT");
            // Update the user's details with the information from the updatedUser

    }

    @Override
    public void removePatient(Long id) {
         userRepository.deleteById(id);

    }
    public User savePatientWithRole(User patient, String role) {
        // Find the profile by role using JPQL
       if(role=="PATIENT") {
           Profile profile = (Profile) profileRepository.findByRole(role);

           if (profile == null)
               throw new IllegalArgumentException("Role not found: " + role);


           // Add the profile to the user's profiles
           patient.getProfiles().add(profile);


       }

        // Save the user
       return userRepository.save(patient);

    }
}
