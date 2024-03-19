package soa.roomservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.roomservice.models.Room;
import soa.roomservice.services.RoomService;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<Room> getRoomById(@RequestParam("id") int id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAll() {
        return ResponseEntity.ok(roomService.getAll());
    }

    @GetMapping
    public ResponseEntity<List<Room>> getRoomWithCapacity(@RequestParam("capacity") int capacity){
        return ResponseEntity.ok(roomService.getRoomWithCapacity(capacity));
    }

    @PostMapping
    public ResponseEntity<Room> saveRoom(
            @RequestParam("id") int id,
            @RequestParam("roomNumber") String roomNumber,
            @RequestParam("description") String description,
            @RequestParam("capacity") int capacity,
            @RequestParam("isOccupied") boolean isOccupied,
            @RequestParam("cost") int cost
    ) {
        return ResponseEntity.ok(roomService.saveRoom(id, roomNumber, description, capacity, isOccupied, cost));
    }

    @GetMapping
    public ResponseEntity<List<Room>> findRoomsByOccupancy(@RequestParam("isOccupied") boolean isOccupied){
        return ResponseEntity.ok(roomService.findRoomByOccupancy(isOccupied));
    }

    @DeleteMapping
    public ResponseEntity<Integer> deleteRoom(@RequestParam("id") int id) {
        return ResponseEntity.ok(roomService.deleteRoom(id));
    }
}
