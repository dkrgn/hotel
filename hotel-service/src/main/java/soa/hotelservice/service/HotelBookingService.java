package soa.hotelservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import soa.hotelservice.dto.booking.BookingRequest;
import soa.hotelservice.dto.booking.BookingResponse;
import soa.hotelservice.dto.notification.NotificationRequest;
import soa.hotelservice.dto.payment.PaymentResponse;
import soa.hotelservice.dto.room.RoomResponse;
import soa.hotelservice.event.BookingEventRequest;
import soa.hotelservice.event.BookingEventResponse;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class HotelBookingService {

    private final WebClient.Builder webClient;
    private final String URI = "http://booking-service/booking";
    private final HotelPaymentService paymentService;
    private final HotelNotificationService notificationService;
    private final HotelRoomService roomService;

    @CircuitBreaker(name = "make-booking", fallbackMethod = "fallBackDeletePayment")
    public BookingEventResponse makeBooking(BookingEventRequest request) {
        PaymentResponse paymentResponse = paymentService.save(request.getPaymentRequest());
        if (paymentResponse.getId() != 0) {
            request.getBookingRequest().setPaymentId(paymentResponse.getId());
            BookingResponse bookingResponse = saveBooking(request.getBookingRequest());
            roomService.changeAvailability(bookingResponse.getRoomId(), false);
            notificationService.save(new NotificationRequest(
                    paymentResponse.getUserId(),
                    "Booking was successfully saved with id " + bookingResponse.getId(),
                    request.getEmail()));
            return new BookingEventResponse(paymentResponse, bookingResponse, request.getEmail());
        } else {
            log.error("Payment could not be processed, error occurred!");
            return new BookingEventResponse();
        }
    }

    public BookingEventResponse fallBackDeletePayment(BookingEventRequest request, RuntimeException e) {
        paymentService.deletePaymentsWithUserId(request.getBookingRequest().getUserId());
        return new BookingEventResponse();
    }

    public BookingResponse saveBooking(BookingRequest request) {
        BookingResponse response = webClient.build()
                .post()
                .uri(URI)
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(BookingResponse.class)
                .block();
        if (response == null){
            throw new IllegalArgumentException("Booking Service could not save a booking!");
        }
        else {
            log.info("The booking with id {} was saved in Booking Service!", response.getId());
            return response;
        }
    }

    public Integer deleteBookingsByUserId(int id) {
        BookingResponse[] bookingResponse = webClient.build()
                .get()
                .uri(URI + "/" + id)
                .retrieve()
                .bodyToMono(BookingResponse[].class)
                .block();
        if (bookingResponse != null) {
            for (BookingResponse resp : bookingResponse) {
                roomService.changeAvailability(resp.getRoomId(), true);
                paymentService.deletePaymentsWithUserId(id);
                notificationService.deleteNotificationsByUserId(id);
                deleteBookings(id);
            }
            return bookingResponse.length;
        } else {
            throw new IllegalArgumentException("Could not delete booking with id " + id);
        }
    }

    public void deleteBookings(int userId) {
        webClient.build()
                .delete()
                .uri(URI + "/" + userId)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
    }
}
