package soa.sessionservice.models;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notifications")
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id",
            nullable = false)
    private int userId;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Column(columnDefinition = "VARCHAR(100)", name = "email")
    private String email;

}
