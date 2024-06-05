package org.example.cliniccare1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "medical_visit_appointments")
public class Medical_Visit_Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mva_id")
    private Long id;

    @Column(name = "mva_date")
    private Date mvaDate;

    @Column(name = "visit_type")
    private String visitType;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

//    private String occupational_medicine_service;


}
