package com.isa.bloodtransfusion.repositories;

import com.isa.bloodtransfusion.models.Complaint;
import com.isa.bloodtransfusion.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findComplaintsByCustomer_Id(Long customerId);

    List<Complaint> findByAnswerNull();

    List<Complaint> findByAdmin(User admin);
}
