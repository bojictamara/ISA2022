package com.isa.bloodtransfusion.payload.requests;

import javax.validation.constraints.NotBlank;

public record ComplaintRequest(
        Long medicalWorkerId,
        Long centerId,
        @NotBlank String text
) {
}
