package com.isa.bloodtransfusion.services;

import com.isa.bloodtransfusion.email.AppointmentConfirmationEmailContext;
import com.isa.bloodtransfusion.exceptions.AppointmentAlreadyCancelledException;
import com.isa.bloodtransfusion.exceptions.AppointmentDoesNotExistsException;
import com.isa.bloodtransfusion.models.Appointment;
import com.isa.bloodtransfusion.models.Center;
import com.isa.bloodtransfusion.models.User;
import com.isa.bloodtransfusion.repositories.AppointmentsRepository;
import com.isa.bloodtransfusion.repositories.CenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CenterService {

    private final CenterRepository centerRepository;
    private final AppointmentsRepository appointmentsRepository;
    private final EmailSendingService emailSendingService;

    public List<Center> getAllCenters() {
        return centerRepository.findAll();
    }

    public Center getCenterById(Long centerId) throws NoSuchElementException {
        return centerRepository.findById(centerId).orElse(null);
    }

    public List<Appointment> getCenterFreeAppointmentsByCenter(Long centerId) {
        return appointmentsRepository.findByCenter_IdAndUserIsNull(centerId);
    }

    public boolean checkAppointmentInPrevious6MonthsExists(User user) {
        return appointmentsRepository.findByStartAfterAndUser(LocalDateTime.now().minusMonths(6), user).isPresent();
    }

    public void reserveAppointment(Long appointmentId, User user) throws AppointmentDoesNotExistsException, AppointmentAlreadyCancelledException {
        var appointment = appointmentsRepository.findById(appointmentId);
        if (appointment.isEmpty()) {
            throw new AppointmentDoesNotExistsException();
        }

        var appointmentEntity = appointment.get();

        for (var u : appointmentEntity.getCancellations()) {
            if (user.equals(u)) {
                throw new AppointmentAlreadyCancelledException();
            }
        }

        appointmentEntity.setUser(user);

        appointmentsRepository.save(appointmentEntity);

        emailSendingService.sendAppointmentConfirmationEmail(appointmentEntity, user);
    }

}
