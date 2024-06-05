package org.example.cliniccare1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "clinics")

public class Clinic  {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "clinic_id")
    private int id;

    @Column(name="name")
    private String name;
    @Column(name = "clinic_email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "place")
    private String place;

    @Column(name = "address")
    private String address;
    @Column(name = "creation_date")
    private Date creationDate;

    @JsonIgnore
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="clinics_companies", joinColumns=@JoinColumn(name="clinic_id"),inverseJoinColumns=@JoinColumn(name="company_id"))
    private List<Company>companies=new ArrayList<>();

    @ManyToMany(mappedBy = "clinics")
    private List<Profile> profiles=new ArrayList<>();

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
