package soa.hotelservice.dto.room;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequest {

    private String roomNumber;
    private String description;
    private Integer capacity;
    private int cost;
    private RoomType type;
}
