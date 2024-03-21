package soa.hotelservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import soa.hotelservice.dto.user.UserRequest;
import soa.hotelservice.dto.user.UserResponse;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class HotelUserService {

    private final WebClient.Builder webClient;
    private static final String URI = "http://user-service/user";

    public UserResponse getUserById(int id) {
        UserResponse response = webClient.build()
                .get()
                .uri(URI + "/" + id)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
        if (response == null) {
            throw new IllegalArgumentException("User Service could not load user with id " + id + "!");
        } else {
            log.info("The user with id {} was successfully retrieved from User Service!", id);
            return response;
        }
    }

    public UserResponse getUserByEmail(String email) {
        UserResponse response = webClient.build()
                .get()
                .uri(URI + "/email=" + email)
                .header("Accept", "application/json")
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
        if (response == null) {
            throw new IllegalArgumentException("User Service could not find user with email " + email + "!");
        } else {
            log.info("The user with id {} was successfully found in User Service!", email);
            return response;
        }
    }

    public List<UserResponse> getAll() {
        UserResponse[] response = webClient.build()
                .get()
                .uri(URI + "/all")
                .retrieve()
                .bodyToMono(UserResponse[].class)
                .block();
        if (response == null) {
            throw new IllegalArgumentException("User Service could not load list of users!");
        } else {
            log.info("The list of users was successfully retrieved from User Service!");
            return Arrays.stream(response).toList();
        }
    }

    public UserResponse saveUser(UserRequest request) {
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
            return response;
        }
    }

    public Integer deleteUser(int id) {
        Integer response = webClient.build()
                .delete()
                .uri(URI + "/" + id)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
        if (response == null) {
            throw new IllegalArgumentException("User Service could not load user with id " + id + "!");
        } else {
            log.info("The user with id {} was successfully retrieved from User Service!", id);
            return response;
        }
    }

    public UserResponse editUser(int id, UserRequest request) {
        UserResponse response = webClient.build()
                .put()
                .uri(URI + "/" + id)
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
        if (response == null) {
            throw new IllegalArgumentException("User Service could not edit user with id " + id);
        } else {
            log.info("The user with id {} was successfully edited in User Service!", id);
            return response;
        }
    }
}
