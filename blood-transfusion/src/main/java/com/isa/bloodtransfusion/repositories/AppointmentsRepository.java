package com.isa.bloodtransfusion.repositories;

import com.isa.bloodtransfusion.models.Appointment;
import com.isa.bloodtransfusion.models.Center;
import com.isa.bloodtransfusion.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentsRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByCenter_IdAndUserIsNull(Long centerId);

    Optional<Appointment> findByStartAfterAndUser(LocalDateTime dateBefore, User user);

    int countByCenterAndUser(Center center, User user);
    List<Appointment> findByUser(User user);
}
