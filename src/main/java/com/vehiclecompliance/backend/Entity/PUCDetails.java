package com.vehiclecompliance.backend.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "puc_details")
public class PUCDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "puc_id")
    private long pucid;

    @Column(name = "certificate_number", nullable = false, unique= true)
    private String pucNumber;

    @Column(name = "valid_from", nullable = false)
    private LocalDate validfrom;

    @Column(name = "valid_till", nullable = false)
    private LocalDate validtill;

    @OneToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    public Long getPucId(){
        return pucid;
    }

    public String getCertificateNumber(){
        return pucNumber;
    }

    public LocalDate getValidFrom(){
        return validfrom;
    }

    public LocalDate getValidTill(){
        return validtill;
    }

    public Vehicle vehicle(){
        return vehicle;
    }
}
