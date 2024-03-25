package soa.bookingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookingResponse {

    private int id;
    private int userId;
    private int roomId;
    private int paymentId;
    private LocalDateTime start;
    private LocalDateTime end;
}
