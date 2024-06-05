package org.example.cliniccare1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "medical_directories")
@Data
public class Medical_Directory {

    @Id
    @Column(name = "MD_Id")
    private int MD_id;
    @Column(name = "creation_date")
    private Date Creation_Date;
    @Column(name = "maj_date")
    private  Date Maj_Date;
    @Column(name = "national_service")
    private String National_Service;
    @Column(name = "congenital_conditions")
    private  String Congenital_Conditions;
    @Column(name = "prof_diseases_lpp")
    private float profesional_diseases_lpp;
    @Column(name = "social_securiy_number")
    private int social_security_num;
    @Column(name = "blood_group")
    private String Blood_Group;
    @Column(name = "rhesus")
    private String rhesus;
    @Column(name = "qualifications")
    private String qualifications;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}
