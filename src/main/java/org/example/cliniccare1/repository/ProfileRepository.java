package org.example.cliniccare1.repository;

import org.example.cliniccare1.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
    public Profile findByRole(String role);
}
