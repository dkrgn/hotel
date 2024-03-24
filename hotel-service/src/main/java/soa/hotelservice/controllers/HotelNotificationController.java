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
    public Mono<ResponseEntity<NotificationResponse>> saveNotification(@RequestBody NotificationRequest request) {
        Mono<ResponseEntity<NotificationResponse>> tmp =  hotelNotificationService.save(request)
                .map(response -> ResponseEntity.ok(response))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
        log.info("Done");
        return  tmp;
    }
}
