package soa.paymentservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.paymentservice.dto.PaymentRequest;
import soa.paymentservice.dto.PaymentResponse;
import soa.paymentservice.service.PaymentService;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentResponse> savePayment(@RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.save(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deletePaymentByRoomId(@PathVariable("id") int id) {
        return ResponseEntity.ok(paymentService.deletePaymentByRoomId(id));
    }
}
