package soa.bookingservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import soa.bookingservice.dto.BookingRequest;
import soa.bookingservice.dto.BookingResponse;
import soa.bookingservice.model.Booking;
import soa.bookingservice.repo.BookingRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class BookingService {

    private final BookingRepo bookingRepo;

    public List<BookingResponse> getAllBookingsByUserId(int id) {
        List<Booking> bookings = bookingRepo.getAllBookingsByUserId(id).orElseThrow(
                () -> new IllegalArgumentException("Bookings with user id " + id + " could not be found in the database!")
        );
        bookings.forEach(System.out::println);
        return bookings.stream().map(this::buildResponse).collect(Collectors.toList());
    }

    public BookingResponse saveBooking(BookingRequest request) {
        Booking booking = Booking.builder()
                .userId(request.getUserId())
                .roomId(request.getRoomId())
                .paymentId(request.getPaymentId())
                .start(request.getStart().withHour(12))
                .end(request.getEnd().withHour(12))
                .build();
        bookingRepo.save(booking);
        Booking fromDB = bookingRepo.getBookingById(booking.getId()).orElseThrow(
                () -> new IllegalArgumentException("User was not saved! Please try again.")
        );
        return buildResponse(booking);
    }

    public BookingResponse editBooking(int id, BookingRequest request) {
        Booking booking = bookingRepo.getBookingById(id).orElseThrow(
                () -> new IllegalArgumentException("Get booking with id " + id + " request resulted in error. Please try again.")
        );
        booking.setStart(request.getStart().withHour(12));
        booking.setEnd(request.getEnd().withHour(12));
        bookingRepo.save(booking);
        log.info("The booking with id {} was successfully edited!", id);
        return buildResponse(booking);
    }

    public Integer deleteBookingById(int id) {
        Booking booking = bookingRepo.getBookingById(id).orElseThrow(
                () -> new IllegalArgumentException("The booking with id " + id + " could not be retrieved from DB or is not present!")
        );
        bookingRepo.delete(booking);
        return booking.getId();
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
