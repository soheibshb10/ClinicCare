package org.example.cliniccare1.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;

import org.example.cliniccare1.dto.UserDTO;
import org.example.cliniccare1.model.Medical_File;
import org.example.cliniccare1.model.Profile;
import org.example.cliniccare1.model.SecurityUser;
import org.example.cliniccare1.model.User;
import org.example.cliniccare1.repository.ProfileRepository;
import org.example.cliniccare1.repository.SecurityUserRepository;
import org.example.cliniccare1.repository.UserRepository;
import org.example.cliniccare1.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private SecurityUserRepository securityUserRepository;
    private ModelMapper modelMapper;
    @Override
    public void saveUser(UserDTO userDTO) {
        ModelMapper modelMapper=new ModelMapper();
        User user=modelMapper.map(userDTO,User.class);
        userRepository.save(user);
    }




    public void saveAllUsers(List<UserDTO> userDTOList) {
        for (UserDTO userDTO:userDTOList)
            saveUser(userDTO);
    }

    @Override
    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }


    @Override
    @Transactional
    public User saveUserWithRole(User user, String role) {
        // Check if a user with the same email already exists
        Optional<User> existingUserOpt = userRepository.findByEmail(user.getEmail());

        User existingUser;
        if (existingUserOpt.isPresent()) {
            // If user exists, use the existing user
            existingUser = existingUserOpt.get();
        } else {
            // If user does not exist, use the new user
            existingUser = user;
        }
        SecurityUser securityUser = modelMapper.map(existingUser, SecurityUser.class);

        // Find or create the profile by role
        Profile profile = profileRepository.findByRole(role);
        if (profile == null) {
            // Create a new profile if it doesn't exist
            profile = new Profile();
            profile.setRole(role);
            // You may want to set other properties of the profile here
            profile = profileRepository.save(profile);
        } else {
            System.out.println("The role: " + role + " already exists");
        }

        // Add the profile to the user's profiles if not already added
        if (!existingUser.getProfiles().contains(profile)) {
            System.out.println("User added successfully to a profile: " + profile.getLabel());

            // Update the bidirectional relationship
            existingUser.getProfiles().add(profile);
            profile.getUsers().add(existingUser);
            // Map User to SecurityUser and save
            securityUser.getProfiles().add(profile); // Add profile to security user
            profile.getSecurityusers().add(securityUser); // Add security user to profile
        }


        securityUser = securityUserRepository.save(securityUser);

        // Save the user entity
        existingUser = userRepository.save(existingUser);

        // Return the saved user entity
        return existingUser;
    }


    public List<Medical_File> getMedicalFilesByUserId(Long userId) {
        return userRepository.findById(userId)
                .map(User::getMedicalFiles)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }

    public User updateUserWithRole(User updatedUser, String role) {
        // Find the profile by role
        Profile profile = profileRepository.findByRole(role);
        if (profile == null)
            throw new IllegalArgumentException("Role not found: " + role);


        // Add the profile to the updated user's profiles
        updatedUser.getProfiles().add(profile);

        // Save the updated user entity
        User savedUser = userRepository.save(updatedUser);

        // Return the updated user entity
        return savedUser;
    }




}
