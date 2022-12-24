package com.isa.bloodtransfusion.repositories;

import com.isa.bloodtransfusion.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
