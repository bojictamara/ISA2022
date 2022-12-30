package com.isa.bloodtransfusion.controllers;

import com.isa.bloodtransfusion.exceptions.AppointmentCancellationNotAllowedException;
import com.isa.bloodtransfusion.exceptions.AppointmentDoesNotExistsException;
import com.isa.bloodtransfusion.payload.responses.AppointmentResponse;
import com.isa.bloodtransfusion.security.UserDetailsImpl;
import com.isa.bloodtransfusion.services.AppointmentService;
import com.isa.bloodtransfusion.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final UserService userService;

    @GetMapping(
            value = "/appointments/my-list",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<List<AppointmentResponse>> getMyAppointments() {
        SecurityContext context = SecurityContextHolder.getContext();
        var authUser = (UserDetailsImpl) context.getAuthentication().getPrincipal();
        var user = userService.findById(authUser.getId());

        return ResponseEntity.ok(
            appointmentService.getAppointmentsReservedByUser(user).stream()
                    .map(a -> new AppointmentResponse(
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

    @PutMapping(
            value = "/appointments/cancel/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<String> cancelAppointment(@PathVariable Long id) {
        SecurityContext context = SecurityContextHolder.getContext();
        var authUser = (UserDetailsImpl) context.getAuthentication().getPrincipal();
        var user = userService.findById(authUser.getId());

        try {
            appointmentService.cancelAppointment(id, user);
            return ResponseEntity.ok("Successful");
        } catch(AppointmentCancellationNotAllowedException ex) {
            return ResponseEntity.badRequest().body("Nije moguce otkazivanje termina manje od 24 sata pre pocetka");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Termin nije pronadjen");
        }
    }
}
