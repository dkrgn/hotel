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

    private NotificationResponse buildResponse(Notification notification) {
        return NotificationResponse.builder()
                .id(notification.getId())
                .userId(notification.getUserId())
                .message(notification.getMessage())
                .email(notification.getEmail())
                .build();
    }

    public NotificationResponse getNotificationById(int id){
        Notification notification = notificationRepo.getNotificationById(id).orElseThrow(
                () -> new IllegalArgumentException("Get notification with id " + id + " request resulted in error. Please try again."));
        return buildResponse(notification);
    }

    public List<NotificationResponse> getAll(){
        List<Notification> notification = notificationRepo.getAll().orElseThrow(
                () -> new IllegalArgumentException("Get all notifications request resulted in error. Please try again."));
        return notification.stream().map(this::buildResponse).collect(Collectors.toList());
    }

    public NotificationResponse save(NotificationRequest request) {
        Notification notification = Notification.builder()
                .userId(request.getUserId())
                .message(request.getMessage())
                .email(request.getEmail())
                .build();
        //TODO: Make a real implementation of email notification?
        log.info("Sending a message: " + notification.getMessage());
        notificationRepo.save(notification);
        Notification fromDB = notificationRepo.getNotificationById(notification.getId()).orElseThrow(
                () -> new IllegalArgumentException("Notification was not saved! Please try again."));
        log.info("The notification with id {} was successfully saved!", notification.getId());
        return buildResponse(fromDB);
    }
}
