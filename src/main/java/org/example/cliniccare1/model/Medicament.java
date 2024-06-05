package org.example.cliniccare1.model;



import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "medicaments")
public class Medicament {
    @Id
    @Column(name = "medicament_id")
    private Long id;

    @Column(name = "brand_name")
    private String brandName; // brand name

    @Column(name = "code")
    private String code; // code

    @Column(name = "form")
    private String form; // form

    @Column(name = "dosage")
    private Float dosage; // Dosage

    // Number times in day
    @Column(name = "ntid")
    private Long numberTimesInDay;

    // Quantity
    @Column(name = "Num_Cans")
    private Long quantity;


    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="medicaments_medical_file", joinColumns=@JoinColumn(name="medicament_id"),inverseJoinColumns=@JoinColumn(name="MF_id"))
    private List<Medical_File> medicalFiles=new ArrayList<>();

}
