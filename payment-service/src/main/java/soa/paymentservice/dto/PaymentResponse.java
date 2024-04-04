package soa.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import soa.paymentservice.model.PaymentType;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {

    private int id;
    private int userId;
    private int roomId;
    private PaymentType type;
    private LocalDateTime issuedAt;
}
