package soa.hotelservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.scheduler.Schedulers;
import soa.hotelservice.dto.notification.NotificationRequest;
import soa.hotelservice.dto.notification.NotificationResponse;

import java.util.concurrent.ExecutionException;

@Service
@Slf4j
@AllArgsConstructor
public class HotelNotificationService {

    private final WebClient.Builder webClient;

    private final String URI = "http://notification-service/notification";

    public void save(NotificationRequest request) {
        try {
            webClient.build()
                    .post()
                    .uri(URI)
                    .body(BodyInserters.fromValue(request))
                    .retrieve()
                    .bodyToMono(NotificationResponse.class)
                    .subscribeOn(Schedulers.single())
                    .toFuture()
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteNotificationsByBookingId(int id) {
        Integer response = webClient.build()
                .delete()
                .uri(URI + "/" + id)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
        if (response == null) {
            throw new IllegalArgumentException("Notifications could not be deleted!");
        }
    }
}
