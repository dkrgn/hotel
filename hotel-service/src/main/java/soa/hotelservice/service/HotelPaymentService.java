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

    public void deletePaymentsWithUserId(int id) {
        Integer response = webClient.build()
                .delete()
                .uri(URI + "/" + id)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
        if (response == null){
            throw new IllegalArgumentException("Payment Service could not a payment or there are no payments present in the database!");
        }
    }
}
