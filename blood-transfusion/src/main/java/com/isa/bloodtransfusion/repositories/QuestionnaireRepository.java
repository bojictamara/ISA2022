package com.isa.bloodtransfusion.repositories;

import com.isa.bloodtransfusion.models.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {

    Optional<Questionnaire> findByUser_Id(Long userId);
}
