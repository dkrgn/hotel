package soa.sessionservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import soa.sessionservice.dto.NotificationRequest;
import soa.sessionservice.dto.NotificationResponse;
import soa.sessionservice.models.Notification;
import soa.sessionservice.repositories.NotificationRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationRepo notificationRepo;

    public NotificationResponse save(NotificationRequest request) {
        Notification notification = Notification.builder()
                .bookingId(request.getBookingId())
                .message(request.getMessage())
                .email(request.getEmail())
                .build();
        log.info("Sending a message: " + notification.getMessage());
        notificationRepo.save(notification);
        Notification fromDB = notificationRepo.getNotificationById(notification.getId()).orElseThrow(
                () -> new IllegalArgumentException("Notification was not saved! Please try again."));
        log.info("The notification with id {} was successfully saved!", notification.getId());
        return buildResponse(fromDB);
    }

    public Integer deleteNotificationByBookingId(int id) {
        Notification notification = notificationRepo.getNotificationByBookingId(id).orElseThrow(
                () -> new IllegalArgumentException("Notification with booking id " + id + " could not be retrieved from the db!")
        );
        notificationRepo.delete(notification);
        return id;
    }

    private NotificationResponse buildResponse(Notification notification) {
        return NotificationResponse.builder()
                .id(notification.getId())
                .bookingId(notification.getBookingId())
                .message(notification.getMessage())
                .email(notification.getEmail())
                .build();
    }
}
