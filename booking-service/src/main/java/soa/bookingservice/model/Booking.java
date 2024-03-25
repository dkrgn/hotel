package soa.bookingservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "room_id", nullable = false)
    private int roomId;

    @Column(name = "payment_id", nullable = false)
    private int paymentId;

    @Column(name = "start_date",
            columnDefinition = "TIMESTAMP",
            nullable = false)
    private LocalDateTime start;

    @Column(name = "end_date",
            columnDefinition = "TIMESTAMP",
            nullable = false)
    private LocalDateTime end;
}
