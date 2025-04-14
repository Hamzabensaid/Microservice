package com.pidev.Pi.PasswordReset;

import com.pidev.Pi.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @Column
    private LocalDateTime usedAt; // Set when the token is used

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Check if the token is still valid
    public boolean isValid() {
        return usedAt == null && expiresAt.isAfter(LocalDateTime.now());
    }
}
