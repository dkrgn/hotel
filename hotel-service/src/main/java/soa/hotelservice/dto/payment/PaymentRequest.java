package soa.hotelservice.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

    private int userId;
    private int roomId;
    private PaymentStatus status;
    private PaymentType type;
    private LocalDateTime issuedAt;
}
