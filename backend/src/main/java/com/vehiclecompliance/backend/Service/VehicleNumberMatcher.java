package com.vehiclecompliance.backend.Service;

import com.vehiclecompliance.backend.Entity.Vehicle;
import com.vehiclecompliance.backend.Repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleNumberMatcher {

    private final VehicleRepository vehicleRepository;

    public VehicleNumberMatcher(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public String findClosestVehicleNumber(String ocrText) {

        List<Vehicle> vehicles = vehicleRepository.findAll();

        String bestMatch = null;
        int bestDistance = Integer.MAX_VALUE;

        for (Vehicle vehicle : vehicles) {

            String vehicleNumber =
                    vehicle.getVehicleNumber().toUpperCase();

            int distance =
                    calculateDistance(ocrText, vehicleNumber);

            if (distance < bestDistance) {
                bestDistance = distance;
                bestMatch = vehicleNumber;
            }
        }

        return bestMatch;
    }

    private int calculateDistance(String first, String second) {

        int[][] dp =
                new int[first.length() + 1][second.length() + 1];

        for (int i = 0; i <= first.length(); i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= second.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= first.length(); i++) {

            for (int j = 1; j <= second.length(); j++) {

                int cost =
                        first.charAt(i - 1) == second.charAt(j - 1)
                                ? 0
                                : 1;

                dp[i][j] = Math.min(
                        Math.min(
                                dp[i - 1][j] + 1,
                                dp[i][j - 1] + 1
                        ),
                        dp[i - 1][j - 1] + cost
                );
            }
        }

        return dp[first.length()][second.length()];
    }
}