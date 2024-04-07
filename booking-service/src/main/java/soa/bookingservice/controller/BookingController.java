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
    public ResponseEntity<List<BookingResponse>> getAllBookingsByUserId(@PathVariable("id") int id) {
        return ResponseEntity.ok(bookingService.getAllBookingsByUserId(id));
    }

    @PostMapping
    public ResponseEntity<BookingResponse> saveBooking(@RequestBody BookingRequest request) {
        return ResponseEntity.ok(bookingService.saveBooking(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResponse> editBooking(
            @PathVariable("id") int id,
            @RequestBody BookingRequest request
    ) {
        return ResponseEntity.ok(bookingService.editBooking(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteBookingById(@PathVariable("id") int id) {
        return ResponseEntity.ok(bookingService.deleteBookingById(id));
    }
}
