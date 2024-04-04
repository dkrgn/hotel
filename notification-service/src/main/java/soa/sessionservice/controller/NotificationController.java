package soa.sessionservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.sessionservice.dto.NotificationRequest;
import soa.sessionservice.dto.NotificationResponse;
import soa.sessionservice.service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/notification")
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> getPaymentById(@PathVariable("id") int id) {
        return ResponseEntity.ok(notificationService.getNotificationById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<NotificationResponse>> getAll() {
        return ResponseEntity.ok(notificationService.getAll());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NotificationResponse> savePayment(@RequestBody NotificationRequest request) {
        return ResponseEntity.ok(notificationService.save(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteNotificationByUserId(@PathVariable("id") int id) {
        return ResponseEntity.ok(notificationService.deleteNotificationByUserId(id));
    }
}
