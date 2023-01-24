package com.isa.bloodtransfusion.controllers;

import com.isa.bloodtransfusion.exceptions.CenterDoesNotExistException;
import com.isa.bloodtransfusion.exceptions.CenterNotVisitedException;
import com.isa.bloodtransfusion.exceptions.ComplaintNotFoundException;
import com.isa.bloodtransfusion.exceptions.UserDoesNotExistException;
import com.isa.bloodtransfusion.models.ERole;
import com.isa.bloodtransfusion.payload.requests.ComplaintRequest;
import com.isa.bloodtransfusion.payload.requests.SaveAnswerRequest;
import com.isa.bloodtransfusion.payload.responses.ComplaintResponse;
import com.isa.bloodtransfusion.security.UserDetailsImpl;
import com.isa.bloodtransfusion.services.ComplaintService;
import com.isa.bloodtransfusion.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintService complaintService;
    private final UserService userService;

    @GetMapping(value = "/my-complaints")
    public ResponseEntity<List<ComplaintResponse>> getEmployees() {
        SecurityContext context = SecurityContextHolder.getContext();
        var authUser = (UserDetailsImpl) context.getAuthentication().getPrincipal();
        var user = userService.findById(authUser.getId());

        return ResponseEntity.ok(complaintService.findComplaintsByCustomerId(user.getId()).stream()
                .map(complaint -> new ComplaintResponse(complaint.getId(),
                        complaint.getTimestamp(),
                        complaint.getCenter() != null ? complaint.getCenter().getId() : null,
                        complaint.getCenter() != null ? complaint.getCenter().getName() : null,
                        complaint.getGuilty() != null ? complaint.getGuilty().getId() : null,
                        complaint.getGuilty() != null ? complaint.getGuilty().getName() + " " + complaint.getGuilty().getLastName() : null,
                        complaint.getAdmin() != null ? complaint.getAdmin().getId() : null,
                        complaint.getAdmin() != null ? complaint.getAdmin().getName() + " " + complaint.getAdmin().getLastName() : null,
                        complaint.getText(),
                        complaint.getAnswer())).toList());
    }

    @GetMapping(value = "/not-answered-complaints")
    public ResponseEntity<List<ComplaintResponse>> getNotAnsweredComplaints() {
        SecurityContext context = SecurityContextHolder.getContext();
        var authUser = (UserDetailsImpl) context.getAuthentication().getPrincipal();
        var user = userService.findById(authUser.getId());

        if (!user.getRole().equals(ERole.ADMIN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(complaintService.findNotAnsweredComplaints().stream()
                .map(complaint -> new ComplaintResponse(complaint.getId(),
                        complaint.getTimestamp(),
                        complaint.getCenter() != null ? complaint.getCenter().getId() : null,
                        complaint.getCenter() != null ? complaint.getCenter().getName() : null,
                        complaint.getGuilty() != null ? complaint.getGuilty().getId() : null,
                        complaint.getGuilty() != null ? complaint.getGuilty().getName() + " " + complaint.getGuilty().getLastName() : null,
                        complaint.getAdmin() != null ? complaint.getAdmin().getId() : null,
                        complaint.getAdmin() != null ? complaint.getAdmin().getName() + " " + complaint.getAdmin().getLastName() : null,
                        complaint.getText(),
                        complaint.getAnswer())).toList());
    }

    @GetMapping(value = "/complaints/admin")
    public ResponseEntity<List<ComplaintResponse>> getComplaintsHistoryForAdmin() {
        SecurityContext context = SecurityContextHolder.getContext();
        var authUser = (UserDetailsImpl) context.getAuthentication().getPrincipal();
        var user = userService.findById(authUser.getId());

        if (!user.getRole().equals(ERole.ADMIN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(complaintService.findComplaintsHistoryForAdmin(user).stream()
                .map(complaint -> new ComplaintResponse(complaint.getId(),
                        complaint.getTimestamp(),
                        complaint.getCenter() != null ? complaint.getCenter().getId() : null,
                        complaint.getCenter() != null ? complaint.getCenter().getName() : null,
                        complaint.getGuilty() != null ? complaint.getGuilty().getId() : null,
                        complaint.getGuilty() != null ? complaint.getGuilty().getName() + " " + complaint.getGuilty().getLastName() : null,
                        complaint.getAdmin() != null ? complaint.getAdmin().getId() : null,
                        complaint.getAdmin() != null ? complaint.getAdmin().getName() + " " + complaint.getAdmin().getLastName() : null,
                        complaint.getText(),
                        complaint.getAnswer())).toList());
    }

    @PutMapping(
            value = "/complaints/answer",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.TEXT_PLAIN_VALUE}
    )
    public ResponseEntity<String> saveAnswer(@Valid @RequestBody SaveAnswerRequest requestBody) {
        SecurityContext context = SecurityContextHolder.getContext();
        var authUser = (UserDetailsImpl) context.getAuthentication().getPrincipal();
        var user = userService.findById(authUser.getId());

        try {
            complaintService.saveAnswer(requestBody, user);
        } catch (ComplaintNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Successful");
    }

    @PostMapping(
            value = "/complaints",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.TEXT_PLAIN_VALUE}
    )
    public ResponseEntity<String> createComplaint(@Valid @RequestBody ComplaintRequest requestBody) {
        SecurityContext context = SecurityContextHolder.getContext();
        var authUser = (UserDetailsImpl) context.getAuthentication().getPrincipal();
        var user = userService.findById(authUser.getId());

        try {
            complaintService.createComplaint(requestBody, user);
        } catch (UserDoesNotExistException e) {
            return ResponseEntity.badRequest().body("User does not exists");
        } catch (CenterDoesNotExistException e) {
            return ResponseEntity.badRequest().body("Center does not exists");
        } catch (CenterNotVisitedException e) {
            if (e.getMessage().equals("center")) {
                return ResponseEntity.badRequest().body("You have never visited selected center");
            } else {
                return ResponseEntity.badRequest().body("You have never visited the center where selected worker works");
            }
        }

        return ResponseEntity.ok("Successful");
    }
}
