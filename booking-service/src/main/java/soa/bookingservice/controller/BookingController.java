package soa.bookingservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.bookingservice.dto.BookingRequest;
import soa.bookingservice.dto.BookingResponse;
import soa.bookingservice.service.BookingService;

@RestController
@RequestMapping("/booking")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponse> saveBooking(@RequestBody BookingRequest request) {
        return ResponseEntity.ok(bookingService.saveBooking(request));
    }
}
