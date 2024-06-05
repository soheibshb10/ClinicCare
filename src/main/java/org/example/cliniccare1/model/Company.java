package org.example.cliniccare1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="companies")
@Data
public class Company {
    @Id
    @Column(name = "company_id")
    private Long Company_id;
    @Column(name = "Company_name")
    private String Company_name;
    @Column(name = "Company_type")
    private String Company_type;
    // Corrected mappedBy attribute
    @ManyToMany(mappedBy="companies",cascade=CascadeType.ALL)
    private List<Clinic> clinics=new ArrayList<>();
}
