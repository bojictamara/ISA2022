package com.isa.bloodtransfusion.payload.requests;

import com.isa.bloodtransfusion.models.Gender;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record RegistrationRequest(
        @NotBlank
        @Size(max = 100)
        String name,

        @NotBlank
        @Size(max = 100)
        String lastName,

        @NotBlank
        @Size(min = 3, max = 20)
        String username,

        @NotBlank
        @Size(max = 50)
        @Email
        String email,

        @NotBlank
        @Size(min = 6, max = 40)
        String password,

        @NotBlank
        @Size(min = 13)
        String jmbg,

        @NotBlank
        @Size(min = 3, max = 100)
        String address,

        @NotBlank
        @Size(min = 3, max = 50)
        String city,

        @NotBlank
        @Size(min = 3, max = 50)
        String state,

        @NotBlank
        @Size(min = 3, max = 20)
        String phone,

        @NotNull
        Gender gender,

        @NotBlank
        String profession,

        String professionInfo


) {
}
