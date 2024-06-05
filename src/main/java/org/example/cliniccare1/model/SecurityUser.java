package org.example.cliniccare1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "security_users")

public class SecurityUser implements UserDetails  {
    private static final long serialVersionUID = -6690946490872875352L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_security_id")
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @ManyToMany(mappedBy="securityusers",cascade=CascadeType.ALL)
    private List<Profile> profiles = new ArrayList<>(); // Profile represents the authority


//    @ManyToMany(mappedBy="securityUsers",cascade=CascadeType.ALL)
//    private List<Clinic> clinics;
//
//
//    @ManyToMany(mappedBy="securityUsers",cascade=CascadeType.ALL)
//    private List<User> users;




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return profiles.stream()
                .map(profile -> new SimpleGrantedAuthority(profile.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    @PrePersist
    @PreUpdate
    private void hashPassword() {
        if (password != null && !password.startsWith("$2a$")) { // Check if the password is already hashed
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            this.password = passwordEncoder.encode(password);
        }
    }

}
