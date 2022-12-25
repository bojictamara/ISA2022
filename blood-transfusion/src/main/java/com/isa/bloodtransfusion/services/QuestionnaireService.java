package com.isa.bloodtransfusion.services;

import com.isa.bloodtransfusion.models.Questionnaire;
import com.isa.bloodtransfusion.models.User;
import com.isa.bloodtransfusion.payload.requests.FilledQuestionnaireRequest;
import com.isa.bloodtransfusion.repositories.QuestionnaireRepository;
import com.isa.bloodtransfusion.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionnaireService {

    private final QuestionnaireRepository questionnaireRepository;

    public Questionnaire findByUserId(Long userId) {
        return questionnaireRepository.findByUser_Id(userId).orElse(null);
    }

    public Questionnaire fillQuestionnaire(FilledQuestionnaireRequest requestBody, User user) {

        var newQuestionnaire = new Questionnaire();
        BeanUtils.copyProperties(requestBody, newQuestionnaire);
        newQuestionnaire.setUser(user);
        return questionnaireRepository.save(newQuestionnaire);
    }

}
