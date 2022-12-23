package com.isa.bloodtransfusion.repositories;

import com.isa.bloodtransfusion.models.SecureToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecureTokenRepository extends JpaRepository<SecureToken, Long> {
    SecureToken findByToken(String token);
    void removeByToken(String token);
}
