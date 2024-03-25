package soa.hotelservice.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import soa.hotelservice.dto.notification.NotificationRequest;
import soa.hotelservice.dto.notification.NotificationResponse;
import soa.hotelservice.service.HotelNotificationService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/notification-api")
@AllArgsConstructor
@Slf4j
public class HotelNotificationController {

    private final HotelNotificationService hotelNotificationService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<NotificationResponse> getNotificationById(@PathVariable("id") int id) {
        return ResponseEntity.ok(hotelNotificationService.getNotificationById(id));
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<NotificationResponse>> getAll() {
        return ResponseEntity.ok(hotelNotificationService.getAll());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<NotificationResponse> saveNotification(@RequestBody NotificationRequest request) {
        log.info("Done");
        return ResponseEntity.ok(hotelNotificationService.save(request));
    }
}
