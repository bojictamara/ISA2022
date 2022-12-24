package com.isa.bloodtransfusion.payload.responses;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public record AppointmentResponse(
        Long id,
        LocalDateTime start,
        Long centerId,
        String centerName,
        Long userId,
        String userName,
        String userLastName
) {
}
