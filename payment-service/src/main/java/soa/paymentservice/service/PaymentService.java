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

    public PaymentResponse getPaymentById(int id) {
        Payment payment = paymentRepo.getPaymentById(id).orElseThrow(
                () -> new IllegalArgumentException("Get payment with id " + id + " request resulted in error. Please try again."));
        return buildResponse(payment);
    }

    public List<PaymentResponse> getAll() {
        List<Payment> list = paymentRepo.getAll().orElseThrow(
                () -> new IllegalArgumentException("Get all payments request resulted in error. Please try again."));
        return list.stream().map(this::buildResponse).collect(Collectors.toList());
    }

    private PaymentResponse buildResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .userId(payment.getUserId())
                .roomId(payment.getRoomId())
                .status(payment.getStatus())
                .type(payment.getType())
                .issuedAt(payment.getIssuedAt())
                .build();
    }

    public PaymentResponse save(PaymentRequest request) {
        Payment payment = Payment.builder()
                .userId(request.getUserId())
                .roomId(request.getRoomId())
                .status(request.getStatus())
                .type(request.getType())
                .issuedAt(request.getIssuedAt())
                .build();
        paymentRepo.save(payment);
        Payment fromDB = paymentRepo.getPaymentById(payment.getId()).orElseThrow(
                () -> new IllegalArgumentException("Payment was not saved! Please try again."));
        log.info("The room with id {} was successfully saved!", payment.getId());
        return buildResponse(fromDB);
    }
}
