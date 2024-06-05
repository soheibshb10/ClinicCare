package org.example.cliniccare1.repository;

import org.example.cliniccare1.model.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentRepository extends JpaRepository<Medicament,Long> {
}
