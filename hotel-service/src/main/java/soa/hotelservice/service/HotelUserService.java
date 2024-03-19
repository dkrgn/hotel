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

    private final WebClient webClient;

    public UserResponse getUserById(int id) {
        UserResponse response = webClient
                .get()
                .uri("http://localhost:8081/user/" + id)
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

    public List<UserResponse> getAll() {
        UserResponse[] response = webClient
                .get()
                .uri("http://localhost:8081/user/all")
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
        UserResponse response = webClient
                .post()
                .uri("http://localhost:8081/user")
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
        Integer response = webClient
                .delete()
                .uri("http://localhost:8081/user/" + id)
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
        UserResponse response = webClient
                .put()
                .uri("http://localhost:8081/user/" + id)
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
