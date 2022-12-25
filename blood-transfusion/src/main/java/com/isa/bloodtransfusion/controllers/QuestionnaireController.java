package com.isa.bloodtransfusion.controllers;

import com.isa.bloodtransfusion.models.Questionnaire;
import com.isa.bloodtransfusion.payload.requests.FilledQuestionnaireRequest;
import com.isa.bloodtransfusion.security.UserDetailsImpl;
import com.isa.bloodtransfusion.services.QuestionnaireService;
import com.isa.bloodtransfusion.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class QuestionnaireController {

    private final QuestionnaireService questionnaireService;
    private final UserService userService;

    @PostMapping(
            value = "/questionnaire",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Questionnaire> fillQuestionnaire(@Valid @RequestBody FilledQuestionnaireRequest requestBody) {

        var context = SecurityContextHolder.getContext();
        var principal = (UserDetailsImpl) context.getAuthentication().getPrincipal();
        var user = userService.findById(principal.getId());

        return ResponseEntity.ok(questionnaireService.fillQuestionnaire(requestBody, user));
    }
}
