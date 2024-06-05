package org.example.cliniccare1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profiles")
@Data
public class Profile {

    @Id
    @Column(name = "profile_id")
    private Long id;

    @Column(name = "label")
    private String label;

    @Column(name = "role")
    private String role;


    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="security_profiles_users", joinColumns=@JoinColumn(name="profile_id"),inverseJoinColumns=@JoinColumn(name="user_id"))
    private List<SecurityUser>securityusers=new ArrayList<>();

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="users_profiles", joinColumns=@JoinColumn(name="profile_id"),inverseJoinColumns=@JoinColumn(name="user_id"))
    private List<User>users=new ArrayList<>();

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="clinics_profiles", joinColumns=@JoinColumn(name="profile_id"),inverseJoinColumns=@JoinColumn(name="user_id"))
    private List<Clinic>clinics=new ArrayList<>();


}
