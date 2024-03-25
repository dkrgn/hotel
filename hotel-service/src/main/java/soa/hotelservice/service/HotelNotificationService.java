package soa.hotelservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import soa.hotelservice.dto.notification.NotificationRequest;
import soa.hotelservice.dto.notification.NotificationResponse;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
@AllArgsConstructor
public class HotelNotificationService {

    private final WebClient.Builder webClient;

    private final String URI = "http://notification-service/notification";
    private final String URIUser = "http://user-service/user";

    public NotificationResponse getNotificationById(int id) {
        NotificationResponse response = webClient.build()
                .get()
                .uri(URI +"/"+ id)
                .retrieve()
                .bodyToMono(NotificationResponse.class)
                .block();
        if (response == null){
            throw new IllegalArgumentException("Notification Service could not load notification with id " + id + "!");
        }
        else{
            log.info("The notification with id {} was successfully retrieved from Notification Service!", id);
            return response;
        }
    }

    public List<NotificationResponse> getAll() {
        NotificationResponse[] response = webClient.build()
                .get()
                .uri(URI + "/all")
                .retrieve()
                .bodyToMono(NotificationResponse[].class)
                .block();
        if (response == null){
            throw new IllegalArgumentException("Notification Service could not load a list of notifications!");
        }
        else{
            log.info("The list of notifications was successfully retrieved from Notification Service!");
            return Arrays.stream(response).toList();
        }
    }

    //Async
    //TODO: Get users email and put it into the request
    public NotificationResponse save(NotificationRequest request) {
        try {
            return webClient.build()
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
}
