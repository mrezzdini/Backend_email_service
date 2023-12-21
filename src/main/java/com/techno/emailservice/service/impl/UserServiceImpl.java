package com.techno.emailservice.service.impl;

import com.techno.emailservice.domain.Confirmation;
import com.techno.emailservice.domain.User;
import com.techno.emailservice.repository.ConfirmationRepo;
import com.techno.emailservice.repository.UserRepo;
import com.techno.emailservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ConfirmationRepo confirmationRepo;

    @Override
    public User saveuser(User user) {
        if(userRepo.existsByEmail(


                user.getEmail())){
            throw new RuntimeException("email already exist");
        }
        user.setEnabled(false);
        userRepo.save(user);

        Confirmation confirmation = new Confirmation(user);
        confirmationRepo.save(confirmation);
        /* send email to the user with token */

        return user;
    }

    @Override
    public Boolean verifyToken(String token) {

        Confirmation confirmation = confirmationRepo.findByToken(token);
        User user = userRepo.findByEmailIgnoreCase(confirmation.getUser().getEmail());
        user.setEnabled(true);
        userRepo.save(user);
        //configurationRepo.delete(confirmation);
        return Boolean.TRUE;
    }
}
