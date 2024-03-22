package soa.hotelservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import soa.hotelservice.dto.payment.PaymentRequest;
import soa.hotelservice.dto.payment.PaymentResponse;
import soa.hotelservice.dto.room.RoomResponse;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class HotelPaymentService {

    private final WebClient.Builder webClient;
    private final String URI = "http://payment-service/payment";

    public PaymentResponse getPaymentById(int id) {
        PaymentResponse response = webClient.build()
                .get()
                .uri(URI +"/"+ id)
                .retrieve()
                .bodyToMono(PaymentResponse.class)
                .block();
        if (response == null){
            throw new IllegalArgumentException("Payment Service could not load room with id " + id + "!");
        }
        else{
            log.info("The payment with id {} was successfully retrieved from Payment Service!", id);
            return response;
        }
    }

    public List<PaymentResponse> getAll() {
        PaymentResponse[] response = webClient.build()
                .get()
                .uri(URI + "/all")
                .retrieve()
                .bodyToMono(PaymentResponse[].class)
                .block();
        if (response == null){
            throw new IllegalArgumentException("Payment Service could not load a list of payments!");
        }
        else{
            log.info("The list of payments was successfully retrieved from Payment Service!");
            return Arrays.stream(response).toList();
        }
    }

    public PaymentResponse save(PaymentRequest request) {
        PaymentResponse response = webClient.build()
                .post()
                .uri(URI)
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(PaymentResponse.class)
                .block();
        if (response == null){
            throw new IllegalArgumentException("Payment Service could not save a payment!");
        }
        else{
            log.info("The payment with id {} was saved in Payment Service!", response.getId());
            return response;
        }
    }
}
