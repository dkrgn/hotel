package soa.roomservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import soa.roomservice.models.RoomType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequest {

    private String roomNumber;
    private String description;
    private Integer capacity;
    private Double cost;
    private RoomType type;
}
