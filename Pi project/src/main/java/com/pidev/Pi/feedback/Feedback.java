package com.pidev.Pi.feedback;

import com.pidev.Pi.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    private int rating; // You can set a range (e.g., 1-5)

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Assuming feedback is linked to a user

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}

