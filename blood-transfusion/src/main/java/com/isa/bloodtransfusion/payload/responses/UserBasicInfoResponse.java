package com.isa.bloodtransfusion.payload.responses;

public record UserBasicInfoResponse(
        String name,
        String lastName,
        Long id
) {
}
