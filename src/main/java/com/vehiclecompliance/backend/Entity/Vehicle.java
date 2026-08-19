package com.vehiclecompliance.backend.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.vehiclecompliance.backend.dto.PUCResponse;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "vehicle_number", nullable = false, unique = true)
    private String vehicleNumber;

    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @Column(name = "vehicle_type", nullable = false)
    private String vehicleType;

    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "variant")
    private String variant;

    @Column(name = "fuel_type", nullable = false)
    private String fuelType;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @Column(name = "manufacturing_year")
    private Integer manufacturingYear;

    @Column(name = "color")
    private String color;

    @Column(name = "rto")
    private String rto;

    @OneToOne(mappedBy = "vehicle")
    private RCDetails rcDetails;

    @OneToOne(mappedBy = "vehicle")
    private InsuranceDetails insuranceDetails;

    @OneToOne(mappedBy = "vehicle")
    private PUCDetails pucDetails;

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getManufacturingYear() {
        return manufacturingYear;
    }

    public void setManufacturingYear(Integer manufacturingYear) {
        this.manufacturingYear = manufacturingYear;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRto() {
        return rto;
    }

    public void setRto(String rto) {
        this.rto = rto;
    }

    public RCDetails getRcDetails() {
        return rcDetails;
    }

    public void setRcDetails(RCDetails rcDetails) {
        this.rcDetails = rcDetails;
    }

    public InsuranceDetails getInsuranceDetails() {
        return insuranceDetails;
    }

    public void setInsuranceDetails(InsuranceDetails insuranceDetails) {
        this.insuranceDetails = insuranceDetails;
    }

    public PUCDetails getPucDetails(){
        return pucDetails;
    }

    public void setPUCDetails(PUCDetails pucDetails){
        this.pucDetails = pucDetails;
    }
}