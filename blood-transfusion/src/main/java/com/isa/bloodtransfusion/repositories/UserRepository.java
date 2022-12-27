package com.isa.bloodtransfusion.repositories;


import com.isa.bloodtransfusion.models.ERole;
import com.isa.bloodtransfusion.models.Role;
import com.isa.bloodtransfusion.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    List<User> findByRole(ERole role);
}
