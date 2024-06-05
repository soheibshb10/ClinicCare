package org.example.cliniccare1.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.example.cliniccare1.model.User;
import org.example.cliniccare1.repository.ProfileRepository;
import org.example.cliniccare1.repository.UserRepository;
import org.example.cliniccare1.service.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
@AllArgsConstructor
@Transactional

public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserServiceImpl userService;

    @Override
    public List<User> getALLDoctors() {
        return userRepository.findUsersByProfileRole("DOCTOR");
    }

    @Override
    public User saveDoctor(User doctor) {
        return userService.saveUserWithRole(doctor,"DOCTOR");
    }

    @Override
    public Optional<User> getDoctorById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateDoctor(User updatedDoctor) {
       return userService.updateUserWithRole(updatedDoctor,"DOCTOR");


    }

    @Override
    public void removeDoctor(Long id) {
        Optional<User> existingDoctor = userRepository.findById(id);
        if (existingDoctor.isPresent()) {
            User user = existingDoctor.get();
            boolean isDoctor = user.getProfiles().stream()
                    .anyMatch(profile -> "DOCTOR".equals(profile.getRole()));
            if (isDoctor) {
                userRepository.deleteById(id);
            } else {
                throw new IllegalArgumentException("User with ID " + id + " is not a doctor.");
            }
        } else {
            throw new NoSuchElementException("User with ID " + id + " does not exist.");
        }
    }




    public User saveDoctorWithRole(User doctor) {

        return userService.saveUserWithRole(doctor,"DOCTOR");

    }


}
