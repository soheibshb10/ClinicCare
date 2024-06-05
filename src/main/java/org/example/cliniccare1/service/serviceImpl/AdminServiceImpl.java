package org.example.cliniccare1.service.serviceImpl;



import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;

import org.example.cliniccare1.model.Medical_File;
import org.example.cliniccare1.model.Profile;
import org.example.cliniccare1.model.User;
import org.example.cliniccare1.repository.ProfileRepository;
import org.example.cliniccare1.repository.UserRepository;
import org.example.cliniccare1.service.AdminService;
import org.example.cliniccare1.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional

public class AdminServiceImpl implements AdminService {
    private UserRepository userRepository;
    private ProfileRepository profileRepository;
    private ModelMapper modelMapper;
    private UserServiceImpl userService;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository, ProfileRepository profileRepository, ModelMapper modelMapper, UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public List<User> getALLAdmins() {
        return null;
    }

    @Override
    public User saveAdmin(User user) {
        return null;
    }

    @Override
    public Optional<User> getAdminById(Long Id) {
        return Optional.empty();
    }

    @Override
    public User updateAdmin(Long id, User updatedUser) {
        return null;
    }

    @Override
    public void removeAdmin(Long id) {

    }

    @Override
    public void removeRole(Long id) {

    }

    @Override
    public List<Profile> getAllRoles() {
        return null;
    }

    @Override
    public User saveAdminWithRole(User admin, String role) {
        return null;
    }

    @Override
    public Profile updateRole(Profile profile) {
        return null;
    }

    @Override
    public ResponseEntity<Profile> addRole(Profile profile) {
        return null;
    }
}