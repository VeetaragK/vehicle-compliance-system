package com.vehiclecompliance.backend.controller;

import com.vehiclecompliance.backend.Service.OCRService;
import com.vehiclecompliance.backend.dto.VehicleResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.vehiclecompliance.backend.Service.VehicleNumberMatcher;
import com.vehiclecompliance.backend.Service.VehicleService;
import com.vehiclecompliance.backend.dto.VehicleResponse;

@RestController
@RequestMapping("/api/ocr")
@CrossOrigin(origins = "http://localhost:5173")
public class OCRController {

    private final OCRService ocrService;
    private final VehicleNumberMatcher vehicleNumberMatcher;
    private final VehicleService vehicleService;

    public OCRController(
        OCRService ocrService,
        VehicleNumberMatcher vehicleNumberMatcher,
        VehicleService vehicleService) {

        this.ocrService = ocrService;
        this.vehicleNumberMatcher = vehicleNumberMatcher;
        this.vehicleService = vehicleService;
    }

    @PostMapping("/vehicle-number")
    public ResponseEntity<String> extractVehicleNumber(
            @RequestParam("image") MultipartFile image) throws Exception {

        String text = ocrService.extractText(image);

        return ResponseEntity.ok(text);
    }

    @PostMapping("/vehicle")
    public ResponseEntity<VehicleResponse> getVehicleFromImage(
            @RequestParam("image") MultipartFile image) throws Exception {

        String ocrText = ocrService.extractText(image);

        String vehicleNumber =
                vehicleNumberMatcher.findClosestVehicleNumber(ocrText);

        VehicleResponse vehicle =
                vehicleService.getVehicleByNumber(vehicleNumber);

        return ResponseEntity.ok(vehicle);
    }
}