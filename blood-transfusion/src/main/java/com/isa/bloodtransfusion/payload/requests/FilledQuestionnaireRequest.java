package com.isa.bloodtransfusion.payload.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record FilledQuestionnaireRequest(
        @NotNull boolean answer1,
        @NotNull boolean answer2,
        @NotNull boolean answer3,
        @NotNull boolean answer4,
        @NotNull boolean answer5,
        @NotNull boolean answer6,
        @NotNull boolean answer7,
        @NotNull boolean answer8,
        @NotNull boolean answer9,
        @NotNull boolean answer10,
        @NotNull boolean answer11,
        @NotNull boolean answer12,
        @NotNull boolean answer13,
        @NotNull boolean answer14,
        @NotNull boolean answer15,
        @NotNull boolean answer16,
        @NotNull boolean answer17,
        @NotNull boolean answer18,
        @NotNull boolean answer19,
        @NotNull boolean answer20,
        @NotNull boolean answer21,
        @NotNull boolean answer22,
        @NotNull boolean answer23,
        boolean answer24,
        boolean answer25,
        boolean answer26
) {
}
