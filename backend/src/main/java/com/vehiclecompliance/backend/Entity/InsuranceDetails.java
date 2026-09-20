package com.vehiclecompliance.backend.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "insurance_details")
public class InsuranceDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insurance_id")
    private Long insuranceId;

    @Column(name = "policy_number", nullable = false, unique = true)
    private String policyNumber;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "valid_from", nullable = false)
    private LocalDate validFrom;

    @Column(name = "valid_till", nullable = false)
    private LocalDate validTill;

    @OneToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    public Long getInsuranceId() {
        return insuranceId;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getCompany() {
        return company;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public LocalDate getValidTill() {
        return validTill;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}