package org.example.cliniccare1.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;

import org.example.cliniccare1.model.User;
import org.example.cliniccare1.repository.ProfileRepository;
import org.example.cliniccare1.repository.UserRepository;
import org.example.cliniccare1.service.EmployeeService;
import org.example.cliniccare1.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional

public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;


    @Override
    public List<User> getALLEmployees() {
        return userRepository.findUsersByProfileRole("EMPLOYEE");
    }

    @Override
    public User saveEmployee(User user) {
        return userService.saveUserWithRole(user,"EMPLOYEE");
    }

    @Override
    public Optional<User> getEmployeeById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateEmployee(Long id, User updatedEmployee) {

        // Retrieve the existing user from the repository using the provided id
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User employee = optionalUser.get();
            boolean isEmployee = employee.getProfiles().stream()
                    .anyMatch(profile -> "EMPLOYEE".equals(profile.getRole()));
            // Update the user's details with the information from the updatedUser
            modelMapper.map(updatedEmployee,employee);
            // Add any other fields that need to be updated

            // Save the updated user back to the repository
            return userRepository.save(employee);
        } else
            throw new ResourceNotFoundException("User with id " + id + " not found");
    }

    @Override
    public void removeEmployee(Long id) {
        Optional<User> existingEmployee = userRepository.findById(id);
        if (existingEmployee.isPresent()) {
            User user = existingEmployee.get();
            boolean isEmployee = user.getProfiles().stream()
                    .anyMatch(profile -> "EMPLOYEE".equals(profile.getRole()));
            if (isEmployee) {
                userRepository.deleteById(id);
            } else {
                throw new IllegalArgumentException("User with ID " + id + " is not a doctor.");
            }
        } else {
            throw new NoSuchElementException("User with ID " + id + " does not exist.");
        }
    }

    public User saveEmployeeWithRole(User employee) {
        return userService.saveUserWithRole(employee,"EMPLOYEE");

        }
        // Save the user

    }

