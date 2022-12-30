package com.isa.bloodtransfusion.services;

import com.isa.bloodtransfusion.exceptions.AppointmentCancellationNotAllowedException;
import com.isa.bloodtransfusion.exceptions.AppointmentDoesNotExistsException;
import com.isa.bloodtransfusion.models.Appointment;
import com.isa.bloodtransfusion.models.User;
import com.isa.bloodtransfusion.repositories.AppointmentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentsRepository appointmentsRepository;

    public List<Appointment> getAppointmentsReservedByUser(User user) {
        return appointmentsRepository.findByUser(user);
    }

    public void cancelAppointment(Long appointmentId, User user) throws Exception {
        var appointmentOptional = appointmentsRepository.findById(appointmentId);
        if (appointmentOptional.isEmpty()) {
            throw new AppointmentDoesNotExistsException();
        }

        var appointment = appointmentOptional.get();

        if (!appointment.getUser().equals(user)) {
            throw new Exception();
        }

        if (!appointment.getStart().minusDays(1).isAfter(LocalDateTime.now())) {
            throw new AppointmentCancellationNotAllowedException();
        }

        appointment.setUser(null);
        appointment.addToCancellationHistory(user);
        appointmentsRepository.save(appointment);
    }
}
