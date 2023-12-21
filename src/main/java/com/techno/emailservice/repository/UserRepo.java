package com.techno.emailservice.repository;

import com.techno.emailservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByEmailIgnoreCase(String email);
    Boolean existsByEmail(String email);

}
