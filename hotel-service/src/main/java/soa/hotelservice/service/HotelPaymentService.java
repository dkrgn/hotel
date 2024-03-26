package soa.hotelservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import soa.hotelservice.dto.payment.PaymentRequest;
import soa.hotelservice.dto.payment.PaymentResponse;

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
            throw new IllegalArgumentException("Payment Service could not load payment with id " + id + "!");
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

    @CircuitBreaker(name = "save-payment", fallbackMethod = "savePaymentFallBack")
    public PaymentResponse save(PaymentRequest request) {
        return webClient.build()
                .post()
                .uri(URI)
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(PaymentResponse.class)
                .block();
    }

    public PaymentResponse savePaymentFallBack(PaymentRequest request, RuntimeException e) {
        return new PaymentResponse();
    }

    public Integer deletePaymentsWithUserId(int id) {
        Integer response = webClient.build()
                .delete()
                .uri(URI + "/" + id)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
        if (response == null){
            throw new IllegalArgumentException("Payment Service could not a payment or there are no payments present in the database!");
        }
        else{
            log.info("The payment with id {} was deleted in Payment Service!", response);
            return response;
        }
    }
}
