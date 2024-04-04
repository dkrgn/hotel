package soa.bookingservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import soa.bookingservice.dto.BookingRequest;
import soa.bookingservice.dto.BookingResponse;
import soa.bookingservice.model.Booking;
import soa.bookingservice.repo.BookingRepo;

import java.util.List;
import java.util.stream.Collectors;

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
        return buildResponse(booking);
    }

    public Integer deleteBookingsByUserId(int id) {
        List<Booking> bookings = bookingRepo.getBookingsByUserId(id).orElseThrow(
                () -> new IllegalArgumentException("Could not get list of bookings with user id " + id)
        );
        bookingRepo.deleteAll(bookings);
        return bookings.size();
    }

    public List<BookingResponse> getBookingsByUserId(int id) {
        List<Booking> bookings = bookingRepo.getBookingsByUserId(id).orElseThrow(
                () -> new IllegalArgumentException("Bookings with user id " + id + " could not be found in the database!")
        );
        return bookings.stream().map(this::buildResponse).collect(Collectors.toList());
    }

    private BookingResponse buildResponse(Booking booking) {
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
