package com.vehiclecompliance.backend.exception;

public class VehicleNotFoundException extends RuntimeException {

    public VehicleNotFoundException(String vehicleNumber) {
        super("Vehicle " + vehicleNumber + " not found");
    }
}