package com.isa.bloodtransfusion.controllers;

import com.isa.bloodtransfusion.payload.responses.AppointmentResponse;
import com.isa.bloodtransfusion.payload.responses.CenterResponse;
import com.isa.bloodtransfusion.services.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class CentersController {

    private final CenterService centerService;

    @GetMapping(
            value = "/centers",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<List<CenterResponse>> getAllCenters() {
        return ResponseEntity.ok(centerService.getAllCenters().stream()
                .map(c -> new CenterResponse(
                        c.getId(),
                        c.getName(),
                        c.getAddress(),
                        c.getDescription(),
                        c.getAverageRate()
                )).toList());
    }

    @GetMapping(
            value = "/centers/{centerId}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<CenterResponse> getCenterById(@PathVariable Long centerId) {
        var center = centerService.getCenterById(centerId);
        if (center == null) {
            return ResponseEntity.notFound().build();
        }

        var centerResponse = new CenterResponse(
                center.getId(),
                center.getName(),
                center.getAddress(),
                center.getDescription(),
                center.getAverageRate());

        return ResponseEntity.ok(centerResponse);
    }

    @GetMapping(
            value = "/centers/{centerId}/free-appointments",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<List<AppointmentResponse>> getFreeAppointmentsByCenter(@PathVariable Long centerId) {
        return ResponseEntity.ok(
                centerService.getCenterFreeAppointmentsByCenter(centerId)
                        .stream().map(a -> new AppointmentResponse(
                                a.getId(),
                                a.getStart(),
                                a.getCenter().getId(),
                                a.getCenter().getName(),
                                a.getUser() != null ? a.getUser().getId() : null,
                                a.getUser() != null ? a.getUser().getName() : null,
                                a.getUser() != null ? a.getUser().getLastName() : null
                        )).toList()
        );
    }
}
