package com.techno.emailservice.service;

import com.techno.emailservice.domain.User;

public interface UserService {

    User saveuser(User user);
    Boolean verifyToken(String token);
}
