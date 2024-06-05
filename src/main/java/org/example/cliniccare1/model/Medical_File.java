package org.example.cliniccare1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "medical_files")
public class Medical_File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mf_id")
    private Long id;

    @Column(name = "prescription")
    private String prescription;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "maj_date")
    private Date majDate;

    @ManyToMany(mappedBy = "medicalFiles")
    private List<Medicament> medicaments=new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}
