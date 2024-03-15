package soa.userservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.userservice.models.User;
import soa.userservice.repositories.UserRepo;
import soa.userservice.services.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<User> getUserById(@RequestParam("id") int id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping
    public ResponseEntity<User> saveUser(
            @RequestParam("id") int id,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("mobileNumber") String mobileNumber
    ) {
        return ResponseEntity.ok(userService.saveUser(id, firstName, lastName, email, mobileNumber));
    }

    @DeleteMapping
    public ResponseEntity<Integer> deleteUser(@RequestParam("id") int id) {
       return ResponseEntity.ok(userService.deleteUser(id));
    }
}
