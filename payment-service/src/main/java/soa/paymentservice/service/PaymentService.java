package soa.paymentservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import soa.paymentservice.dto.PaymentRequest;
import soa.paymentservice.dto.PaymentResponse;
import soa.paymentservice.model.Payment;
import soa.paymentservice.repo.PaymentRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepo paymentRepo;

    public PaymentResponse save(PaymentRequest request) {
        Payment payment = Payment.builder()
                .userId(request.getUserId())
                .roomId(request.getRoomId())
                .type(request.getType())
                .issuedAt(request.getIssuedAt())
                .build();
        System.out.println(payment.toString());
        paymentRepo.save(payment);
        Payment fromDB = paymentRepo.getPaymentById(payment.getId()).orElseThrow(
                () -> new IllegalArgumentException("Payment was not saved! Please try again."));
        log.info("The payment with id {} was successfully saved!", payment.getId());
        return buildResponse(fromDB);
    }

    public Integer deletePaymentByRoomId(int id) {
        return paymentRepo.deletePaymentsByRoomId(id).orElseThrow(
                () -> new IllegalArgumentException("Either no payments with room_id" + id + " or error occurred!")
        );
    }

    private PaymentResponse buildResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .userId(payment.getUserId())
                .roomId(payment.getRoomId())
                .type(payment.getType())
                .issuedAt(payment.getIssuedAt())
                .build();
    }
}
