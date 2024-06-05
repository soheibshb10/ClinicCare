package org.example.cliniccare1.service;

import org.example.cliniccare1.model.Profile;
import org.example.cliniccare1.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    //to get all clinics
    public List<User> getALLAdmins();

    //to save admin
    public User saveAdmin(User user);

    // to get admin using id
    public Optional<User> getAdminById(Long Id);

    // to update admin using id
    public User updateAdmin(Long id, User updatedUser);

    // to remove user
    void removeAdmin(Long id);
    ResponseEntity<Profile> addRole(Profile profile);
    void removeRole(Long id);
    List<Profile> getAllRoles();
    User saveAdminWithRole(User admin, String role);
    Profile updateRole(Profile profile);
}
