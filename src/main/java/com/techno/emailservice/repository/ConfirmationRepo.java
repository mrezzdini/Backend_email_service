package com.techno.emailservice.repository;

import com.techno.emailservice.domain.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationRepo extends JpaRepository<Confirmation, Long> {

    Confirmation findByToken(String token);

}