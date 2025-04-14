package com.pidev.Pi.PasswordReset;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class PasswordResetController {

    private final PasswordResetTokenService passwordResetTokenService;

    // ✅ Request password reset
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        String response = passwordResetTokenService.createPasswordResetToken(email);
        return ResponseEntity.ok(response);
    }

    // ✅ Reset the password
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        String response = passwordResetTokenService.resetPassword(token, newPassword);
        return ResponseEntity.ok(response);
    }
}
