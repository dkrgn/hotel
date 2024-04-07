package soa.hotelservice.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import soa.hotelservice.dto.booking.BookingResponse;
import soa.hotelservice.dto.room.RoomResponse;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EditBookingEventResponse {

    private BookingResponse bookingResponse;
    private RoomResponse roomResponse;
}