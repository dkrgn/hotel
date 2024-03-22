package soa.hotelservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.hotelservice.dto.payment.*;
import soa.hotelservice.service.HotelPaymentService;

import java.util.List;

@RestController
@RequestMapping("/payment-api")
@AllArgsConstructor
public class HotelPaymentController {

    private final HotelPaymentService hotelPaymentService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable("id") int id) {
        return ResponseEntity.ok(hotelPaymentService.getPaymentById(id));
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PaymentResponse>> getAll() {
        return ResponseEntity.ok(hotelPaymentService.getAll());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PaymentResponse> savePayment(@RequestBody PaymentRequest request) {
        return ResponseEntity.ok(hotelPaymentService.save(request));
    }
}
