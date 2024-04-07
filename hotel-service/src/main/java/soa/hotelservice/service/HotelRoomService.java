package soa.hotelservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import soa.hotelservice.dto.room.RoomRequest;
import soa.hotelservice.dto.room.RoomResponse;
import soa.hotelservice.dto.room.RoomType;
import soa.hotelservice.dto.user.UserResponse;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class HotelRoomService {

    private final WebClient.Builder webClient;
    private final String URI = "http://room-service/room";

    public RoomResponse getRoomById(int id){
        RoomResponse response = webClient.build()
                .get()
                .uri(URI +"/"+ id)
                .retrieve()
                .bodyToMono(RoomResponse.class)
                .block();
        if (response == null){
            throw new IllegalArgumentException("Room Service could not load room with id " + id + "!");
        }
        else {
            log.info("The room with id {} was successfully retrieved from Room Service!", id);
            return response;
        }
    }

    public List<RoomResponse> getAvailable() {
        RoomResponse[] response = webClient.build()
                .get()
                .uri(URI + "/available")
                .retrieve()
                .bodyToMono(RoomResponse[].class)
                .block();
        if (response == null){
            throw new IllegalArgumentException("Room Service could not load an available list of rooms!");
        }
        else{
            log.info("The list of available rooms was successfully retrieved from Room Service!");
            return Arrays.stream(response).toList();
        }
    }

    public RoomResponse saveRoom(RoomRequest roomRequest){
        RoomResponse response = webClient.build()
                .post()
                .uri(URI)
                .body(BodyInserters.fromValue(roomRequest))
                .retrieve()
                .bodyToMono(RoomResponse.class)
                .block();
        if (response == null){
            throw new IllegalArgumentException("Room Service could not save a room!");
        }
        else{
            log.info("The room with id {} was saved in Room Service!", response.getId());
            return response;
        }
    }

    public Integer deleteRoom(int id){
        Integer response = webClient.build()
                .delete()
                .uri(URI + "/" + id)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
        if (response == null){
            throw new IllegalArgumentException("Room Service could not delete a room!");
        }
        else{
            log.info("The room with id {} was deleted from Room Service!", id);
            return response;
        }
    }

    public void changeAvailability(int roomId, boolean isAvailable) {
        Integer response = webClient.build()
                .patch()
                .uri(URI + "/"  + roomId)
                .body(BodyInserters.fromValue(isAvailable))
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
        if (response == null){
            throw new IllegalArgumentException("Could not change room availability!");
        }
    }
}
