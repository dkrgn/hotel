package soa.paymentservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_id",
            nullable = false)
    private int userId;

    @Column(name = "room_id",
            nullable = false)
    private int roomId;

    @Column(name = "status",
            nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(name = "type",
            nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType type;

    @Column(name = "issued_at",
            columnDefinition = "TIMESTAMP",
            nullable = false)
    private LocalDateTime issuedAt;

}
