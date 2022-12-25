package com.isa.bloodtransfusion.controllers;

import com.isa.bloodtransfusion.exceptions.ExistingEmailException;
import com.isa.bloodtransfusion.exceptions.ExistingUsernameException;
import com.isa.bloodtransfusion.exceptions.InvalidTokenException;
import com.isa.bloodtransfusion.payload.requests.LoginRequest;
import com.isa.bloodtransfusion.payload.requests.RegistrationRequest;
import com.isa.bloodtransfusion.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class AuthController {
    @Value("${front.app.url}")
    private String frontendUrl;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest requestBody) {
        try {
            return ResponseEntity.ok(authService.authenticateUser(requestBody));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(
            value = "/register",
            produces = {MediaType.TEXT_PLAIN_VALUE}
    )
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegistrationRequest requestBody,
                                               HttpServletRequest request) {
        try {
            authService.registerUser(requestBody, request);
            return ResponseEntity.ok("Successfully registered");
        } catch (ExistingUsernameException ex) {
            return ResponseEntity
                    .badRequest()
                    .body("Username is already taken");
        } catch (ExistingEmailException emailException) {
            return ResponseEntity
                    .badRequest()
                    .body("Email is already in use");
        }
    }

    @GetMapping("/verify")
    public void verifyCustomer(@RequestParam(required = false) String token,
                               final Model model,
                               RedirectAttributes redirAttr,
                               HttpServletResponse response) {
        if (token.isEmpty()) {
            frontendUrl += "verification-failed";

        } else {
            try {
                authService.verifyUser(token);
                frontendUrl += "verification-successful";
            } catch (InvalidTokenException e) {
                frontendUrl += "verification-failed";
            }
        }

        response.setStatus(302);
        response.setHeader("Location", frontendUrl);
    }

}
