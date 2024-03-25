package soa.hotelservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soa.hotelservice.event.BookingEventRequest;
import soa.hotelservice.event.BookingEventResponse;
import soa.hotelservice.service.HotelBookingService;

@RestController
@RequestMapping("/booking-api")
@AllArgsConstructor
public class HotelBookingController {

    private final HotelBookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingEventResponse> makeBooking(@RequestBody BookingEventRequest request) {
        return ResponseEntity.ok(bookingService.makeBooking(request));
    }
}
