package soa.paymentservice.controller;

import jakarta.ws.rs.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.paymentservice.dto.PaymentRequest;
import soa.paymentservice.dto.PaymentResponse;
import soa.paymentservice.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable("id") int id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PaymentResponse>> getAll() {
        return ResponseEntity.ok(paymentService.getAll());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentResponse> savePayment(@RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.save(request));
    }
}
