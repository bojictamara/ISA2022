package com.isa.bloodtransfusion.security;

import com.isa.bloodtransfusion.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        var optUser = userRepository.findByUsername(username);
        if (optUser.isPresent()) {
            return UserDetailsImpl.build(optUser.get());
        } else {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
    }
}
