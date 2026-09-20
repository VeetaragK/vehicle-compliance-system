package com.vehiclecompliance.backend.Service;

import com.vehiclecompliance.backend.Entity.InsuranceDetails;
import com.vehiclecompliance.backend.Entity.PUCDetails;
import com.vehiclecompliance.backend.Entity.RCDetails;
import com.vehiclecompliance.backend.Entity.Vehicle;
import com.vehiclecompliance.backend.dto.InsuranceResponse;
import com.vehiclecompliance.backend.dto.PUCResponse;
import com.vehiclecompliance.backend.dto.RCResponse;
import com.vehiclecompliance.backend.dto.VehicleResponse;
import com.vehiclecompliance.backend.Repository.VehicleRepository;
import org.springframework.stereotype.Service;
import com.vehiclecompliance.backend.exception.VehicleNotFoundException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    private String calculateStatus(LocalDate validTill) {

        LocalDate today = LocalDate.now();

        long daysRemaining = ChronoUnit.DAYS.between(today, validTill);

        if (daysRemaining < 0) {
            return "EXPIRED";
        }

        if (daysRemaining <= 30) {
            return "EXPIRING_SOON";
        }

        return "VALID";
    }

    public VehicleResponse getVehicleByNumber(String vehicleNumber) {

        Optional<Vehicle> vehicleOptional =
            vehicleRepository.findByVehicleNumber(vehicleNumber);

        if (vehicleOptional.isEmpty()) {
            throw new VehicleNotFoundException(vehicleNumber);
        }

        Vehicle vehicle = vehicleOptional.get();

        RCDetails rcDetails = vehicle.getRcDetails();
        RCResponse rcResponse = null;

        if (rcDetails != null) {
            String status = calculateStatus(rcDetails.getValidTill());

            rcResponse = new RCResponse(
                rcDetails.getRcNumber(),
                rcDetails.getValidFrom(),
                rcDetails.getValidTill(),
                status
            );
        }
        
        InsuranceDetails insuranceDetails = vehicle.getInsuranceDetails();
        InsuranceResponse insuranceResponse = null;
        

        if(insuranceDetails != null){
            String status = calculateStatus(insuranceDetails.getValidTill());

            insuranceResponse = new InsuranceResponse(
                insuranceDetails.getPolicyNumber(),
                insuranceDetails.getCompany(),
                insuranceDetails.getValidFrom(),
                insuranceDetails.getValidTill(),
                status
            );
        }

        PUCDetails pucDetails = vehicle.getPucDetails();
        PUCResponse pucResponse = null;

        if(pucDetails != null){
            String status = calculateStatus(pucDetails.getValidTill());

            pucResponse = new PUCResponse(
                pucDetails. getCertificateNumber(),
                pucDetails.getValidFrom(),
                pucDetails.getValidTill(),
                status
            );
        }


        VehicleResponse response = new VehicleResponse(
                vehicle.getVehicleNumber(),
                vehicle.getOwnerName(),
                vehicle.getVehicleType(),
                vehicle.getManufacturer(),
                vehicle.getModel(),
                vehicle.getVariant(),
                vehicle.getFuelType(),
                vehicle.getRegistrationDate(),
                vehicle.getManufacturingYear(),
                vehicle.getColor(),
                vehicle.getRto(),
                rcResponse,
                insuranceResponse,
                pucResponse
        );

        return response;
    }
}