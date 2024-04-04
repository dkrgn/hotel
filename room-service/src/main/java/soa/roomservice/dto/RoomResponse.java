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
public class RoomResponse {

    private int id;
    private String roomNumber;
    private String description;
    private Integer capacity;
    private Double price;
    private RoomType type;
    private boolean isAvailable;
}
