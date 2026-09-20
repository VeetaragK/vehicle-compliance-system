package com.vehiclecompliance.backend.dto;

import java.time.LocalDate;

public class RCResponse {

    private String rcNumber;
    private LocalDate validFrom;
    private LocalDate validTill;
    private String status;

    public RCResponse(
            String rcNumber,
            LocalDate validFrom,
            LocalDate validTill,
            String status) {

        this.rcNumber = rcNumber;
        this.validFrom = validFrom;
        this.validTill = validTill;
        this.status = status;
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

    public String getStatus() {
        return status;
    }
}