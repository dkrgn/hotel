package soa.hotelservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.hotelservice.dto.booking.BookingRequest;
import soa.hotelservice.dto.booking.BookingResponse;
import soa.hotelservice.event.EditBookingEventResponse;
import soa.hotelservice.event.MakeBookingEventRequest;
import soa.hotelservice.event.MakeBookingEventResponse;
import soa.hotelservice.service.HotelBookingService;

import java.util.List;

@RestController
@RequestMapping("/booking-api")
@AllArgsConstructor
public class HotelBookingController {

    private final HotelBookingService bookingService;

    @GetMapping("/{id}")
    public ResponseEntity<List<EditBookingEventResponse>> getBookingsAndRoomsByUserId(@PathVariable("id") int userId) {
        return ResponseEntity.ok(bookingService.getBookingsAndRoomsByUserId(userId));
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<MakeBookingEventResponse> makeBooking(@RequestBody MakeBookingEventRequest request) {
        return ResponseEntity.ok(bookingService.makeBooking(request));
    }

    @DeleteMapping("/all/{id}")
    public ResponseEntity<Integer> deleteAllBookingsByUserId(@PathVariable("id") int id) {
        return ResponseEntity.ok(bookingService.deleteAllBookingsByUserId(id));
    }

    @DeleteMapping("/{b_id}/{r_id}")
    public ResponseEntity<Integer> deleteBookingById(
            @PathVariable("b_id") int bookingId,
            @PathVariable("r_id") int roomId) {
        return ResponseEntity.ok(bookingService.deleteBookingById(bookingId, roomId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResponse> editBooking(
            @PathVariable("id") int id,
            @RequestBody BookingRequest request) {
        return ResponseEntity.ok(bookingService.editBooking(id, request));
    }
}