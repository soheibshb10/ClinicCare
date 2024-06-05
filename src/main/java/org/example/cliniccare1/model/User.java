package org.example.cliniccare1.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "birth_date")
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private Sex sex;

    @Column(name = "address")
    private String address;

//    @Column(name = "profile_image")
//    private byte[] image;

    @Column(name = "position_held")
    private String positionHeld;

    @Column(name = "speciality")
    private String speciality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    @ManyToMany(mappedBy = "users",cascade = CascadeType.ALL)
    private List<Profile> profiles=new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private Medical_Directory medicalDirectory;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Medical_Visit_Appointment> medicalVisitAppointments=new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Medical_File> medicalFiles=new ArrayList<>();


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
