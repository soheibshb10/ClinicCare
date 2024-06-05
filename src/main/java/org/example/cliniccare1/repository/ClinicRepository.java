package org.example.cliniccare1.repository;

import org.example.cliniccare1.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClinicRepository extends JpaRepository<Clinic,Long> {
    public List<Clinic> findByEmail(String email);
}
