package soa.bookingservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.bookingservice.dto.BookingRequest;
import soa.bookingservice.dto.BookingResponse;
import soa.bookingservice.service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/booking")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/{id}")
    public ResponseEntity<List<BookingResponse>> getBookingsByUserId(@PathVariable("id") int id) {
        return ResponseEntity.ok(bookingService.getBookingsByUserId(id));
    }

    @PostMapping
    public ResponseEntity<BookingResponse> saveBooking(@RequestBody BookingRequest request) {
        return ResponseEntity.ok(bookingService.saveBooking(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteBookingsByUserId(@PathVariable("id") int id) {
        return ResponseEntity.ok(bookingService.deleteBookingsByUserId(id));
    }
}
