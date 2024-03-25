package soa.bookingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingRequest {
    private int userId;
    private int roomId;
    private int paymentId;
    private LocalDateTime start;
    private LocalDateTime end;
}
