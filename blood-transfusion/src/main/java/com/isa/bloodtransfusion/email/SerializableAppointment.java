package com.isa.bloodtransfusion.email;

import javax.persistence.*;

public record SerializableAppointment(
        Long id,
        String start,
        String centerName,
        String streetName,
        String streetNumber,
        String city,
        String state
) {
}
