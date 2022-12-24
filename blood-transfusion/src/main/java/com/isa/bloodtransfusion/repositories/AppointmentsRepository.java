package com.isa.bloodtransfusion.repositories;

import com.isa.bloodtransfusion.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentsRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByCenter_IdAndUserIsNull(Long centerId);
}
