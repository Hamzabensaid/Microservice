package com.pidev.Pi.PasswordReset;

import com.pidev.Pi.email.EmailService;
import com.pidev.Pi.user.User;
import com.pidev.Pi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenService {

    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    // ✅ Generate & send a password reset token
    public String createPasswordResetToken(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new IllegalStateException("User not found");
        }

        User user = userOptional.get();
        String token = UUID.randomUUID().toString(); // Generate a random token
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(30); // Token valid for 30 minutes

        PasswordResetToken resetToken = PasswordResetToken.builder()
                .token(token)
                .createdAt(LocalDateTime.now())
                .expiresAt(expiresAt)
                .user(user)
                .build();

        passwordResetTokenRepository.save(resetToken);

        // ✅ Send the email with the reset link (using static HTML email)
        String resetLink = "http://localhost:4200/reset-password?token=" + token;
        String subject = "Password Reset Request";

        // Call the sendEmail method for sending a static email with the reset link
        emailService.sendEmail(user.getEmail(), subject, resetLink);

        return "Password reset link sent to your email.";
    }

    // ✅ Validate the token before allowing password reset
    public User validateResetToken(String token) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalStateException("Invalid or expired token"));

        if (!resetToken.isValid()) {
            throw new IllegalStateException("Token is expired or already used");
        }

        return resetToken.getUser();
    }

    // ✅ Reset the password
    public String resetPassword(String token, String newPassword) {
        User user = validateResetToken(token);

        // Update the user's password
        user.setPassword(newPassword); // You should hash the password before saving
        userRepository.save(user);

        // Mark the token as used
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token).get();
        resetToken.setUsedAt(LocalDateTime.now());
        passwordResetTokenRepository.save(resetToken);

        return "Password reset successful!";
    }
}

