package com.isa.bloodtransfusion.repositories;

import com.isa.bloodtransfusion.models.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {
}
