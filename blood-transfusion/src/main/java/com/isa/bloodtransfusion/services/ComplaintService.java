package com.isa.bloodtransfusion.services;

import com.isa.bloodtransfusion.exceptions.CenterDoesNotExistException;
import com.isa.bloodtransfusion.exceptions.CenterNotVisitedException;
import com.isa.bloodtransfusion.exceptions.ComplaintNotFoundException;
import com.isa.bloodtransfusion.exceptions.UserDoesNotExistException;
import com.isa.bloodtransfusion.models.Complaint;
import com.isa.bloodtransfusion.models.User;
import com.isa.bloodtransfusion.payload.requests.ComplaintRequest;
import com.isa.bloodtransfusion.payload.requests.SaveAnswerRequest;
import com.isa.bloodtransfusion.repositories.AppointmentsRepository;
import com.isa.bloodtransfusion.repositories.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final UserService userService;
    private final CenterService centerService;
    private final AppointmentsRepository appointmentsRepository;
    private final EmailSendingService emailSendingService;


    public void createComplaint(ComplaintRequest requestBody, User customer) throws UserDoesNotExistException, CenterDoesNotExistException, CenterNotVisitedException {
        var complaintBuilder = Complaint.builder();
        complaintBuilder.customer(customer);

        if (requestBody.medicalWorkerId() != null) {

            var medicalWorker = userService.findById(requestBody.medicalWorkerId());
            if (medicalWorker == null) {
                throw new UserDoesNotExistException();
            }

            for (var center : medicalWorker.getCenters()) {
                var numberOfAppointments = appointmentsRepository.countByCenterAndUser(center, customer);
                if (numberOfAppointments == 0) {
                    throw new CenterNotVisitedException("medical worker");
                }
            }

            complaintBuilder.guilty(medicalWorker);
        }

        if (requestBody.centerId() != null) {
            var center = centerService.getCenterById(requestBody.centerId());
            if (center == null) {
                throw new CenterDoesNotExistException();
            }

            var numberOfAppointments = appointmentsRepository.countByCenterAndUser(center, customer);
            if (numberOfAppointments == 0) {
                throw new CenterNotVisitedException("center");
            }

            complaintBuilder.center(center);
        }

        complaintBuilder.timestamp(LocalDateTime.now());
        complaintBuilder.text(requestBody.text());

        complaintRepository.save(complaintBuilder.build());
    }

    public List<Complaint> findComplaintsByCustomerId(Long userId) {
        return complaintRepository.findComplaintsByCustomer_Id(userId);
    }

    public List<Complaint> findNotAnsweredComplaints() {
        return complaintRepository.findByAnswerNull();
    }

    public void saveAnswer(SaveAnswerRequest requestBody, User admin) throws ComplaintNotFoundException {
        var complaintOptional = complaintRepository.findById(requestBody.complaintId());
        if (complaintOptional.isEmpty()) {
            throw new ComplaintNotFoundException();
        }

        var complaint = complaintOptional.get();
        complaint.setAdmin(admin);
        complaint.setAnswer(requestBody.answer());
        complaintRepository.save(complaint);

        emailSendingService.sendComplaintAnswerEmail(complaint);

    }

    public List<Complaint> findComplaintsHistoryForAdmin(User user) {
        return complaintRepository.findByAdmin(user);
    }
}
