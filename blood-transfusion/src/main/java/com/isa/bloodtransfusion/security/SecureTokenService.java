package com.isa.bloodtransfusion.security;

import com.isa.bloodtransfusion.models.SecureToken;

public interface SecureTokenService {
    SecureToken createSecureToken();

    void saveSecureToken(SecureToken token);

    SecureToken findByToken(String token);

    void removeToken(SecureToken token);

    void removeTokenByToken(String token);
}
