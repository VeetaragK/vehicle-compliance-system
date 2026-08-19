package com.vehiclecompliance.backend.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rc_details")
public class RCDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rc_id")
    private Long rcId;

    @Column(name = "rc_number", nullable = false, unique = true)
    private String rcNumber;

    @Column(name = "valid_from", nullable = false)
    private LocalDate validFrom;

    @Column(name = "valid_till", nullable = false)
    private LocalDate validTill;

    @OneToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    public Long getRcId() {
        return rcId;
    }

    public String getRcNumber() {
        return rcNumber;
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