package soa.sessionservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.sessionservice.dto.NotificationRequest;
import soa.sessionservice.dto.NotificationResponse;
import soa.sessionservice.service.NotificationService;

@RestController
@RequestMapping("/notification")
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NotificationResponse> saveNotification(@RequestBody NotificationRequest request) {
        return ResponseEntity.ok(notificationService.save(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteNotificationByBookingId(@PathVariable("id") int id) {
        return ResponseEntity.ok(notificationService.deleteNotificationByBookingId(id));
    }
}
