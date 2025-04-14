package com.pidev.Pi.Auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:4200") // Allow requests from Angular frontend

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
 public class AuthenticationController {
    @Autowired

     AuthenticationService service;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register
            (@RequestBody @Valid RegistrationRequest request
            ) throws Throwable {
        service.register(request);
        return ResponseEntity.accepted().build();


    }
    @PostMapping("/authenticate")
     public ResponseEntity<AuthenticationResponse> authenticate(
              @RequestBody @Valid AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/activate-account")
    public void confirm(
            @RequestParam String token
    ) throws MessagingException {
        service.activateAccount(token);
    }


}
