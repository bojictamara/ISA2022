package com.isa.bloodtransfusion.services;

import com.isa.bloodtransfusion.exceptions.ExistingEmailException;
import com.isa.bloodtransfusion.exceptions.ExistingUsernameException;
import com.isa.bloodtransfusion.exceptions.InvalidTokenException;
import com.isa.bloodtransfusion.models.ERole;
import com.isa.bloodtransfusion.models.User;
import com.isa.bloodtransfusion.payload.requests.LoginRequest;
import com.isa.bloodtransfusion.payload.requests.RegistrationRequest;
import com.isa.bloodtransfusion.payload.responses.LoginResponse;
import com.isa.bloodtransfusion.repositories.UserRepository;
import com.isa.bloodtransfusion.security.JwtUtils;
import com.isa.bloodtransfusion.security.SecureTokenService;
import com.isa.bloodtransfusion.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final SecureTokenService secureTokenService;
    private final EmailSendingService emailSendingService;

    public LoginResponse authenticateUser(LoginRequest requestBody) throws Exception {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestBody.username(),
                        requestBody.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        var userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // TODO - uncomment
//        var user = userRepository.findById(userDetails.getId());
//        if (!user.get().isAccountVerified()) {
//            throw new Exception("Account not verified");
//        }

        String jwt = jwtUtils.generateJwtToken(authentication);
        String role = userDetails.getAuthorities().stream().findFirst().get().getAuthority();

        return new LoginResponse(
                jwt,
                userDetails.getId(),
                userDetails.getName(),
                userDetails.getSurname(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                role);
    }


    public void registerUser(RegistrationRequest requestBody,
                             HttpServletRequest request) throws ExistingUsernameException, ExistingEmailException {


        if (userRepository.existsByUsername(requestBody.username())) {
            throw new ExistingUsernameException();
        }

        if (userRepository.existsByEmail(requestBody.email())) {
           throw new ExistingEmailException();
        }

        var user = User.builder()
                .username(requestBody.username())
                .email(requestBody.email())
                .password(encoder.encode(requestBody.password())).build();

        user.setName(requestBody.name());
        user.setLastName(requestBody.lastName());
        user.setRole(ERole.REGULAR_USER);
        userRepository.save(user);

        emailSendingService.sendRegistrationConfirmationEmail(
                ServletUriComponentsBuilder.fromRequestUri(request)
                        .replacePath(null)
                        .build()
                        .toUriString(),
                user);
    }

    public boolean verifyUser(String token) throws InvalidTokenException {
        var secureToken = secureTokenService.findByToken(token);
        if(Objects.isNull(secureToken) || !token.equals(secureToken.getToken()) || secureToken.isExpired()){
            throw new InvalidTokenException("Token is not valid");
        }
        var userOpt = userRepository.findById(secureToken.getUser().getId());
        if(userOpt.isEmpty()){
            return false;
        }
        User user = userOpt.get();
        user.setAccountVerified(true);
        userRepository.save(user);

        secureTokenService.removeToken(secureToken);
        return true;
    }
}
