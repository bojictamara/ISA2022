package com.isa.bloodtransfusion.services;

import com.isa.bloodtransfusion.models.User;
import com.isa.bloodtransfusion.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
