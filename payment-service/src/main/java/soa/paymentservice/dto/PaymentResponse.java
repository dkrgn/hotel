package soa.paymentservice.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import soa.paymentservice.model.PaymentStatus;
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
    private PaymentStatus status;
    private PaymentType type;
    private LocalDateTime issuedAt;
}
