package soa.hotelservice.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.hotelservice.dto.user.*;
import soa.hotelservice.service.HotelUserService;

import java.util.List;

@RestController
@RequestMapping("/user-api")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class HotelUserController {

    private final HotelUserService hotelUserService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable("id") int id
    ) {
        return ResponseEntity.ok(hotelUserService.getUserById(id));
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(hotelUserService.getAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponse> saveUser(
            @RequestBody UserRequest request) {
        return ResponseEntity.ok(hotelUserService.saveUser(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteUser(@PathVariable("id") int id) {
        return ResponseEntity.ok(hotelUserService.deleteUser(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> editUser(
            @Valid @PathVariable("id") int id,
            @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(hotelUserService.editUser(id, request));
    }
}
