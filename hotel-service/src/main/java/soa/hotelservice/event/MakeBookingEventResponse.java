package soa.hotelservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import soa.hotelservice.dto.booking.BookingResponse;
import soa.hotelservice.dto.payment.PaymentResponse;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MakeBookingEventResponse {

    private PaymentResponse paymentResponse;
    private BookingResponse bookingResponse;
    private String email;
}
