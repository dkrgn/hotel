package soa.hotelservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
import soa.hotelservice.dto.user.UserResponse;
import soa.hotelservice.event.EditBookingEventResponse;
import soa.hotelservice.event.MakeBookingEventRequest;
import soa.hotelservice.event.MakeBookingEventResponse;

import java.util.ArrayList;
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
    public MakeBookingEventResponse makeBooking(MakeBookingEventRequest request) {
        PaymentResponse paymentResponse = paymentService.save(request.getPaymentRequest());
        if (paymentResponse.getId() != 0) {
            request.getBookingRequest().setPaymentId(paymentResponse.getId());
            BookingResponse bookingResponse = saveBooking(request.getBookingRequest());
            roomService.changeAvailability(bookingResponse.getRoomId(), false);
            notificationService.save(new NotificationRequest(
                    bookingResponse.getId(),
                    "Booking was successfully saved with id " + bookingResponse.getId(),
                    request.getEmail()));
            return new MakeBookingEventResponse(paymentResponse, bookingResponse, request.getEmail());
        } else {
            log.error("Payment could not be processed, error occurred!");
            return new MakeBookingEventResponse();
        }
    }

    public MakeBookingEventResponse fallBackDeletePayment(MakeBookingEventRequest request, RuntimeException e) {
        paymentService.deletePaymentsWithUserId(request.getBookingRequest().getUserId());
        return new MakeBookingEventResponse();
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

    public Integer deleteAllBookingsByUserId(int id) {
        BookingResponse[] bookingResponse = webClient.build()
                .get()
                .uri(URI + "/" + id)
                .retrieve()
                .bodyToMono(BookingResponse[].class)
                .block();
        if (bookingResponse != null) {
            for (BookingResponse response : bookingResponse) {
                deleteBookingById(response.getId(), response.getRoomId());
            }
            return bookingResponse.length;
        } else {
            throw new IllegalArgumentException("Could not delete booking with id " + id);
        }
    }

    public void delete(int id) {
        webClient.build()
                .delete()
                .uri(URI + "/" + id)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
    }

    public void fullDeletion(int bookingId, int roomId) {
        roomService.changeAvailability(roomId, true);
        paymentService.deletePaymentsWithUserId(roomId);
        notificationService.deleteNotificationsByBookingId(bookingId);
        delete(bookingId);
    }

    public Integer deleteBookingById(int bookingId, int roomId) {
        fullDeletion(bookingId, roomId);
        return bookingId;
    }

    public List<EditBookingEventResponse> getBookingsAndRoomsByUserId(int userId) {
        BookingResponse[] bookingResponse = webClient.build()
                .get()
                .uri(URI + "/" + userId)
                .retrieve()
                .bodyToMono(BookingResponse[].class)
                .block();
        if (bookingResponse == null){
            throw new IllegalArgumentException("Booking Service could not load booking with user id " + userId + "!");
        } else {
            List<EditBookingEventResponse> eventResponseList = new ArrayList<>();
            for (BookingResponse response : bookingResponse) {
                eventResponseList.add(new EditBookingEventResponse(response, roomService.getRoomById(response.getRoomId())));
                log.info("The booking with id {} was successfully retrieved from Booking Service!", response.getId());
            }
            return eventResponseList;
        }
    }

    public BookingResponse editBooking(int id, BookingRequest request) {
        BookingResponse response = webClient.build()
                .put()
                .uri(URI + "/" + id)
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(BookingResponse.class)
                .block();
        if (response == null) {
            throw new IllegalArgumentException("Booking Service could not edit booking with id " + id);
        } else {
            log.info("The booking with id {} was successfully edited in Booking Service!", id);
            return response;
        }
    }
}
