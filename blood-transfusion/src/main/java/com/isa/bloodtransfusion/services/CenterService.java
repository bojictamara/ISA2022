package com.isa.bloodtransfusion.services;

import com.isa.bloodtransfusion.models.Appointment;
import com.isa.bloodtransfusion.models.Center;
import com.isa.bloodtransfusion.repositories.AppointmentsRepository;
import com.isa.bloodtransfusion.repositories.CenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CenterService {

    private final CenterRepository centerRepository;
    private final AppointmentsRepository appointmentsRepository;

    public List<Center> getAllCenters() {
        return centerRepository.findAll();
    }

    public Center getCenterById(Long centerId) throws NoSuchElementException {
        return centerRepository.findById(centerId).orElse(null);
    }

    public List<Appointment> getCenterFreeAppointmentsByCenter(Long centerId) {
        return appointmentsRepository.findByCenter_IdAndUserIsNull(centerId);
    }
}
