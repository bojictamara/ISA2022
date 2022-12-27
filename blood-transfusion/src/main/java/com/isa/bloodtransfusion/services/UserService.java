package com.isa.bloodtransfusion.services;

import com.isa.bloodtransfusion.models.ERole;
import com.isa.bloodtransfusion.models.Role;
import com.isa.bloodtransfusion.models.User;
import com.isa.bloodtransfusion.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findEmployees() {
        return userRepository.findByRole(ERole.MEDICAL_WORKER);
    }
}
