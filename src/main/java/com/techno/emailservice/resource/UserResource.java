package com.techno.emailservice.resource;

import com.techno.emailservice.domain.HttpResponse;
import com.techno.emailservice.domain.User;
import com.techno.emailservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserResource   {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<HttpResponse> createUser(@RequestBody User user){
        User newUser = userService.saveuser(user);
        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .date(Map.of("user", newUser))
                        .message("user created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );

    }


    @GetMapping
    public ResponseEntity<HttpResponse> confirmUserAccount(@RequestParam("token") String token){
        Boolean isSuccess = userService.verifyToken(token);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .date(Map.of("success", isSuccess))
                        .message("Accouont verified")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }
}
