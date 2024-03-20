package soa.hotelservice.dto.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponse {

    private int id;
    private String roomNumber;
    private String description;
    private Integer capacity;
    private int cost;
    private RoomType type;
}
