package soa.hotelservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import soa.hotelservice.dto.booking.BookingRequest;
import soa.hotelservice.dto.payment.PaymentRequest;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingEventRequest {

    private BookingRequest bookingRequest;
    private PaymentRequest paymentRequest;
    private String email;
}
