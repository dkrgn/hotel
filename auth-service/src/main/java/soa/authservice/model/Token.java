package soa.authservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tokens")
@Builder
@Data
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false,
            name = "user_id")
    private int userId;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false,
    name = "created_at")
    private LocalDateTime createdAt;

    @Column(nullable = false,
    name = "expires_at")
    private LocalDateTime expiresAt;

}
