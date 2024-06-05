package org.example.cliniccare1.repository;

import org.example.cliniccare1.model.SecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecurityUserRepository extends JpaRepository<SecurityUser,Long> {
    public Optional<SecurityUser> findByEmail(String email);

}
