package soa.roomservice.controllers;

import jakarta.ws.rs.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.roomservice.dto.RoomRequest;
import soa.roomservice.dto.RoomResponse;
import soa.roomservice.models.RoomType;
import soa.roomservice.services.RoomService;

import java.util.List;

@RestController
@RequestMapping("/room")
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RoomResponse> getRoomById(@PathVariable int id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RoomResponse>> getAll() {
        return ResponseEntity.ok(roomService.getAll());
    }

    @GetMapping("/available")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RoomResponse>> getAvailable() {
        return ResponseEntity.ok(roomService.getAvailable());
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> changeRoomAvailability(
            @RequestBody Boolean isAvailable,
            @PathVariable("id") int id) {
        System.err.println(isAvailable + "   " + id);
        return ResponseEntity.ok(roomService.changeAvailability(id, isAvailable));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RoomResponse> saveRoom(@RequestBody RoomRequest request) {
        return ResponseEntity.ok(roomService.saveRoom(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteRoom(@PathVariable int id) {
        return ResponseEntity.ok(roomService.deleteRoom(id));
    }
}
