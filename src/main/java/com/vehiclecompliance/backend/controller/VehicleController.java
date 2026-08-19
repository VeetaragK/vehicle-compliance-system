package com.vehiclecompliance.backend.controller;

import com.vehiclecompliance.backend.dto.VehicleResponse;
import com.vehiclecompliance.backend.Service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/{vehicleNumber}")
    public Optional<VehicleResponse> getVehicle(
            @PathVariable String vehicleNumber) {

        return vehicleService.getVehicleByNumber(vehicleNumber);
    }
}