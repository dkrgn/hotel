package soa.userservice.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.userservice.dto.UserRequest;
import soa.userservice.dto.UserResponse;
import soa.userservice.models.User;
import soa.userservice.repositories.UserRepo;
import soa.userservice.services.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/hotel")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping(
            value = "/user",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> saveUser(
            @RequestParam("id") int id,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("mobileNumber") String mobileNumber,
            @RequestParam("password") String password
    ) {
        return ResponseEntity.ok(userService.saveUser(id, firstName, lastName, mobileNumber, email, password));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Integer> deleteUser(@RequestParam("id") int id) {
       return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PutMapping("/user/edit/{id}")
    public ResponseEntity<UserResponse> editUser(
            @Valid @PathVariable("id") int id,
            @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(userService.editUser(id, request));
    }

}
