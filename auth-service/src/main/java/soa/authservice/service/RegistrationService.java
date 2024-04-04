package soa.authservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import soa.authservice.dto.register.RegistrationRequest;
import soa.authservice.dto.register.RegistrationResponse;
import soa.authservice.dto.token.TokenRequest;
import soa.authservice.dto.user.UserResponse;

@Service
@AllArgsConstructor
@Slf4j
public class RegistrationService {

    private final WebClient.Builder webClient;
    private static final String URI = "http://user-service/user";
    private final EmailValidatorService validator;
    private final TokenService tokenService;

    public RegistrationResponse register(RegistrationRequest request) {
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
                return new RegistrationResponse(true, response.getEmail(), tokenService.saveToken(new TokenRequest(response.getId())));
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
