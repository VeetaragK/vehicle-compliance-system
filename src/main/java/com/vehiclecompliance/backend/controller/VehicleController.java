package com.vehiclecompliance.backend.controller;

import com.vehiclecompliance.backend.dto.VehicleResponse;
import com.vehiclecompliance.backend.Service.VehicleService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

// import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/{vehicleNumber}")
    public VehicleResponse getVehicle(
            @PathVariable String vehicleNumber) {

        vehicleNumber = vehicleNumber.toUpperCase();

        if (!vehicleNumber.matches("^[A-Z]{2}[0-9]{2}[A-Z]{1,3}[0-9]{1,4}$")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid vehicle number format"
            );
        }

        return vehicleService.getVehicleByNumber(vehicleNumber);
    }
}