package com.vehiclecompliance.backend.dto;

import java.time.LocalDate;

public class PUCResponse {
    private String pucNumber;
    private LocalDate validFrom;
    private LocalDate validTill;
    private String status;

    public PUCResponse(
        String pucNumber,
        LocalDate validFrom,
        LocalDate validTill,
        String status){

            this.pucNumber = pucNumber;
            this.validFrom = validFrom;
            this.validTill = validTill;
            this.status = status;
    }

    public String getPucNumber(){
        return pucNumber;
    }

    public LocalDate getValidFrom(){
        return validFrom;
    }

    public LocalDate getValidTill(){
        return validTill;
    }

    public String getStatus(){
        return status;
    }
}
