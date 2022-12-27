package com.isa.bloodtransfusion.payload.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record SaveAnswerRequest (
        @NotNull Long complaintId,
        @NotEmpty String answer
) {
}
