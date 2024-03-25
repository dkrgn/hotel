package soa.hotelservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import soa.hotelservice.dto.booking.BookingRequest;
import soa.hotelservice.dto.booking.BookingResponse;
import soa.hotelservice.dto.notification.NotificationRequest;
import soa.hotelservice.dto.payment.PaymentResponse;
import soa.hotelservice.event.BookingEventRequest;
import soa.hotelservice.event.BookingEventResponse;

@Service
@AllArgsConstructor
@Slf4j
public class HotelBookingService {

    private final WebClient.Builder webClient;
    private final String URI = "http://booking-service/booking";
    private final HotelPaymentService paymentService;
    private final HotelNotificationService notificationService;

    public BookingEventResponse makeBooking(BookingEventRequest request) {
        PaymentResponse paymentResponse = paymentService.save(request.getPaymentRequest());
        if (paymentResponse == null) {
            throw new IllegalArgumentException("Payment could not be processed. Try again later.");
        } else {
            request.getBookingRequest().setPaymentId(paymentResponse.getId());
            BookingResponse bookingResponse = saveBooking(request.getBookingRequest());
            if (bookingResponse == null) {
                Integer deleteResponse = paymentService.deletePaymentsWithUserId(request.getBookingRequest().getUserId());
                if (deleteResponse == null) {
                    log.info("Payment associated with user id {} was removed from the database", request.getPaymentRequest().getUserId());
                } else {
                    log.error("Payment either not present in Payment service DB or error occurred!");
                }
            } else {
                notificationService.save(new NotificationRequest(
                        paymentResponse.getUserId(),
                        request.getEmail(),
                        "Booking was successfully saved with id " + bookingResponse.getId()));
                return new BookingEventResponse(paymentResponse, bookingResponse, request.getEmail());
            }
        }
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
}
