package com.isa.bloodtransfusion.payload.responses;

import java.time.LocalDateTime;

public record ComplaintResponse (
    Long id,
    LocalDateTime timestamp,
    Long centerId,
    String centerName,
    Long guiltyId,
    String guiltyName,
    Long adminId,
    String adminName,
    String text,
    String answer
) {
}
