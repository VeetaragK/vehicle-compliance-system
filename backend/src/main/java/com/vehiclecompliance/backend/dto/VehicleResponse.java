package com.vehiclecompliance.backend.dto;

import java.time.LocalDate;

public class VehicleResponse {

    private String vehicleNumber;
    private String ownerName;
    private String vehicleType;
    private String manufacturer;
    private String model;
    private String variant;
    private String fuelType;
    private LocalDate registrationDate;
    private Integer manufacturingYear;
    private String color;
    private String rto;

    private RCResponse rc;
    private InsuranceResponse insurance;
    private PUCResponse puc;

    public VehicleResponse(
            String vehicleNumber,
            String ownerName,
            String vehicleType,
            String manufacturer,
            String model,
            String variant,
            String fuelType,
            LocalDate registrationDate,
            Integer manufacturingYear,
            String color,
            String rto,
            RCResponse rc,
            InsuranceResponse insurance,
            PUCResponse puc) {

        this.vehicleNumber = vehicleNumber;
        this.ownerName = ownerName;
        this.vehicleType = vehicleType;
        this.manufacturer = manufacturer;
        this.model = model; 
        this.variant = variant;
        this.fuelType = fuelType;
        this.registrationDate = registrationDate;
        this.manufacturingYear = manufacturingYear;
        this.color = color;
        this.rto = rto;
        this.rc = rc;
        this.insurance = insurance;
        this.puc = puc;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getVariant() {
        return variant;
    }

    public String getFuelType() {
        return fuelType;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public Integer getManufacturingYear() {
        return manufacturingYear;
    }

    public String getColor() {
        return color;
    }

    public String getRto() {
        return rto;
    }

    public RCResponse getRc() {
        return rc;
    }

    public InsuranceResponse getInsurance(){
        return insurance;
    }

    public PUCResponse getPuc(){
        return puc;
    }
}