package com.isa.bloodtransfusion.payload.responses;

import com.isa.bloodtransfusion.models.Address;

public record CenterResponse(Long id,
                             String name,
                             Address address,
                             String description,
                             double averageRate) {
}
