package soa.hotelservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.hotelservice.dto.room.RoomRequest;
import soa.hotelservice.dto.room.RoomResponse;
import soa.hotelservice.dto.room.RoomType;
import soa.hotelservice.service.HotelRoomService;

import java.util.List;

@RestController
@RequestMapping("/room-api")
@AllArgsConstructor
public class HotelRoomController {

    private final HotelRoomService hotelRoomService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RoomResponse> getRoomById(@PathVariable int id) {
        return ResponseEntity.ok(hotelRoomService.getRoomById(id));
    }

    @GetMapping("/available")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RoomResponse>> getAvailable() {
        return ResponseEntity.ok(hotelRoomService.getAvailable());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RoomResponse> saveRoom(@RequestBody RoomRequest request) {
        return ResponseEntity.ok(hotelRoomService.saveRoom(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteRoom(@PathVariable int id) {
        return ResponseEntity.ok(hotelRoomService.deleteRoom(id));
    }
}
