package com.isa.bloodtransfusion.controllers;

import com.isa.bloodtransfusion.payload.responses.UserBasicInfoResponse;
import com.isa.bloodtransfusion.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;
    @GetMapping(value = "/employees")
    public ResponseEntity<List<UserBasicInfoResponse>> getEmployees() {
        return ResponseEntity.ok(userService.findEmployees().stream()
                .map(user -> new UserBasicInfoResponse(user.getName(), user.getLastName(), user.getId())).toList());
    }


}
