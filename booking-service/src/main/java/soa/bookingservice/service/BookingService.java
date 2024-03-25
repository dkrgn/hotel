package soa.bookingservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import soa.bookingservice.dto.BookingRequest;
import soa.bookingservice.dto.BookingResponse;
import soa.bookingservice.model.Booking;
import soa.bookingservice.repo.BookingRepo;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRepo bookingRepo;

    public BookingResponse saveBooking(BookingRequest request) {
        Booking booking = Booking.builder()
                .userId(request.getUserId())
                .roomId(request.getRoomId())
                .paymentId(request.getPaymentId())
                .start(request.getStart())
                .end(request.getEnd())
                .build();
        System.out.println(request.toString());
        System.out.println(booking.toString());
        bookingRepo.save(booking);
        Booking fromDB = bookingRepo.getBookingById(booking.getId()).orElseThrow(
                () -> new IllegalArgumentException("User was not saved! Please try again.")
        );
        return BookingResponse.builder()
                .id(booking.getId())
                .userId(booking.getUserId())
                .roomId(booking.getRoomId())
                .paymentId(booking.getPaymentId())
                .start(booking.getStart())
                .end(booking.getEnd())
                .build();
    }

}
