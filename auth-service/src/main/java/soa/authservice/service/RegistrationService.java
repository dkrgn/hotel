package soa.authservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import soa.authservice.dto.RegistrationRequest;
import soa.authservice.dto.RegistrationResponse;
import soa.authservice.dto.UserRequest;
import soa.authservice.dto.UserResponse;

@Service
@AllArgsConstructor
@Slf4j
public class RegistrationService {

    private final WebClient.Builder webClient;
    private static final String URI = "http://user-service/user";
    private final EmailValidatorService validator;

    public RegistrationResponse register(RegistrationRequest request) {
        System.out.println(request);
        System.out.println(request.getFirstName());
        System.out.println(request.getLastName());
        System.out.println(request.getMobileNumber());
        System.out.println(request.getEmail());
        System.out.println(request.getPassword());
        boolean isValidEmail = validator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("Provided email is invalid " + request.getEmail());
        }
        if (checkIfPresent(request.getEmail())) {
            request.setPassword(PasswordEncoder.getPasswordEncoded(request.getPassword()));
            UserResponse response = webClient.build()
                    .post()
                    .uri(URI)
                    .body(BodyInserters.fromValue(request))
                    .retrieve()
                    .bodyToMono(UserResponse.class)
                    .block();
            if (response == null) {
                throw new IllegalArgumentException("User Service could not save user!");
            } else {
                log.info("The user with id {} was successfully saved in User Service!", response.getId());
                return new RegistrationResponse(true);
            }
        } else {
            throw new IllegalArgumentException("The user with email " + request.getEmail() + " is already present in the system.");
        }
    }

    public boolean checkIfPresent(String email) {
        UserResponse response = webClient.build()
                .get()
                .uri(URI + "?email=" + email)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
        return response == null;
    }
}
