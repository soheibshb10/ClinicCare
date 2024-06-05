package org.example.cliniccare1.repository;

import org.example.cliniccare1.model.Profile;
import org.example.cliniccare1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u JOIN u.profiles p WHERE p.role = :role")
    List<User> findUsersByProfileRole(@Param("role") String role);
    @Query("SELECT p FROM Profile p JOIN p.users u WHERE p.role = :role")
    List<Profile> findProfilesByRole(@Param("role") String role);

    public Optional<User> findByEmail(String email);

}
