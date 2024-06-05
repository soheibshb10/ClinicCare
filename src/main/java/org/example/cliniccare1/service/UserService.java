package org.example.cliniccare1.service;


import org.example.cliniccare1.dto.UserDTO;
import org.example.cliniccare1.model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);
    void saveAllUsers(List<UserDTO>userDTOList);
    List<User> allUsers();
    User saveUserWithRole(User user, String role);
    User updateUserWithRole(User updatedUser, String role);
}
