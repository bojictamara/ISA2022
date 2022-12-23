package com.isa.bloodtransfusion.payload.responses;

public record LoginResponse(
        String token,
        Long id,
        String name,
        String lastName,
        String username,
        String email,
        String role
) { }
