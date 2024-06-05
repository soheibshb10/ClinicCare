package org.example.cliniccare1.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.example.cliniccare1.model.SecurityUser;
import org.example.cliniccare1.model.User;
import org.example.cliniccare1.repository.SecurityUserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SecurityUserRepository userRepository;


    @Override
    @Transactional

    public UserDetails loadUserByUsername(String username) {
        Optional<SecurityUser> optionalUser = userRepository.findByEmail(username);
        SecurityUser user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User details not found for user: " + username));
        return user;
    }
}
