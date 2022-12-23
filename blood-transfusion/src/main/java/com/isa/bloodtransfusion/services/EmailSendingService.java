package com.isa.bloodtransfusion.services;

import com.isa.bloodtransfusion.email.AccountVerificationEmailContext;
import com.isa.bloodtransfusion.models.User;
import com.isa.bloodtransfusion.repositories.SecureTokenRepository;
import com.isa.bloodtransfusion.security.SecureTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class EmailSendingService {
    private final SecureTokenService secureTokenService;

    private final SecureTokenRepository tokenRepository;

    private final EmailService emailService;

    private final SecureTokenService tokenService;

    public void sendRegistrationConfirmationEmail(String baseUrl, User user) {
        var secureToken = tokenService.createSecureToken();
        secureToken.setUser(user);
        tokenRepository.save(secureToken);
        var emailContext = new AccountVerificationEmailContext();
        emailContext.init(user);
        emailContext.setToken(secureToken.getToken());
        emailContext.buildVerificationUrl(baseUrl, secureToken.getToken());
        try {
            emailService.sendMail(emailContext);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
