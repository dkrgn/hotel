package soa.hotelservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.hotelservice.event.BookingEventRequest;
import soa.hotelservice.event.BookingEventResponse;
import soa.hotelservice.service.HotelBookingService;

import java.util.List;

@RestController
@RequestMapping("/booking-api")
@AllArgsConstructor
public class HotelBookingController {

    private final HotelBookingService bookingService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BookingEventResponse> makeBooking(@RequestBody BookingEventRequest request) {
        return ResponseEntity.ok(bookingService.makeBooking(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteBookingsByUserId(@PathVariable("id") int id) {
        return ResponseEntity.ok(bookingService.deleteBookingsByUserId(id));
    }
}
