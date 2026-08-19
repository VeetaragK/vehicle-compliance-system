package com.vehiclecompliance.backend.dto;

import java.time.LocalDate;

public class InsuranceResponse {

    private String policyNumber;
    private String company;
    private LocalDate validFrom;
    private LocalDate validTill;
    private String status;

    public InsuranceResponse(
            String policyNumber,
            String company,
            LocalDate validFrom,
            LocalDate validTill,
            String status) {

        this.policyNumber = policyNumber;
        this.company = company;
        this.validFrom = validFrom;
        this.validTill = validTill;
        this.status = status;
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

    public String getStatus() {
        return status;
    }
}