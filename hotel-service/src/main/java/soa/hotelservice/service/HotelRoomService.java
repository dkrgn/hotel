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

    private final WebClient webClient;
    private final String URI = "http://localhost:8082/room";

    public RoomResponse getRoomById(int id){
        RoomResponse response = webClient.get()
                .uri(URI +"/"+ id)
                .retrieve()
                .bodyToMono(RoomResponse.class)
                .block();
        if (response == null){
            throw new IllegalArgumentException("Room Service could not load room with id " + id + "!");
        }
        else{
            log.info("The room with id {} was successfully retrieved from Room Service!", id);
            return response;
        }
    }

    public List<RoomResponse> getAll(){
        RoomResponse[] response = webClient.get()
                .uri(URI + "/all")
                .retrieve()
                .bodyToMono(RoomResponse[].class)
                .block();
        if (response == null){
            throw new IllegalArgumentException("Room Service could not load a list of rooms!");
        }
        else{
            log.info("The list of rooms was successfully retrieved from Room Service!");
            return Arrays.stream(response).toList();
        }
    }

    public List<RoomResponse> getRoomWithCapacity(int capacity){
        RoomResponse[] response = webClient.get()
                .uri(URI + "?capacity=" + capacity)
                .retrieve()
                .bodyToMono(RoomResponse[].class)
                .block();
        if (response == null){
            throw new IllegalArgumentException("Room Service could not load a list of rooms with capacity " + capacity + "!");
        }
        else{
            log.info("The list of rooms with capacity {} was successfully retrieved from Room Service!", capacity);
            return Arrays.stream(response).toList();
        }
    }

    public RoomResponse saveRoom(RoomRequest roomRequest){
        RoomResponse response = webClient
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
        Integer response = webClient
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

    public List<RoomResponse> getRoomByType(RoomType type){
        RoomResponse[] response = webClient.get()
                .uri(URI + "?type=" + type.name())
                .retrieve()
                .bodyToMono(RoomResponse[].class)
                .block();
        if (response == null){
            throw new IllegalArgumentException("Room Service could not load a list of rooms with type " + type.name() + "!");
        }
        else{
            log.info("The list of rooms with type {} was successfully retrieved from Room Service!", type.name());
            return Arrays.stream(response).toList();
        }
    }

}
