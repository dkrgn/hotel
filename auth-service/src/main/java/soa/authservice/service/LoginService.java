package soa.authservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import soa.authservice.dto.LoginRequest;
import soa.authservice.dto.LoginResponse;
import soa.authservice.dto.UserResponse;

@Service
@AllArgsConstructor
@Slf4j
public class LoginService {

    private final WebClient.Builder webClient;
    private static final String URI = "http://user-service/user";

    public LoginResponse checkCredentials(LoginRequest request) {
        UserResponse response = webClient.build()
                .get()
                .uri(URI + "?email=" + request.getEmail())
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
        if (response == null) {
            throw new IllegalArgumentException("User Service could not find user with email " + request.getEmail() + "!");
        } else {
            String passwordEncoded = PasswordEncoder.getPasswordEncoded(request.getPassword());
            if (!response.getPassword().equals(passwordEncoded)) {
                throw new IllegalStateException("wrong password");
            }
            return new LoginResponse(true);
        }
    }
}
