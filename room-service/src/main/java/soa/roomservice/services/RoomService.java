package soa.roomservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import soa.roomservice.dto.RoomRequest;
import soa.roomservice.dto.RoomResponse;
import soa.roomservice.models.Room;
import soa.roomservice.models.RoomType;
import soa.roomservice.repositories.RoomRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class RoomService {
    private final RoomRepo roomRepo;

    public RoomResponse getRoomById(int id){
        Room r = roomRepo.getRoomById(id).orElseThrow(
                () -> new IllegalArgumentException("Get room with id " + id + " request resulted in error. Please try again."));
        return new RoomResponse(r.getId(), r.getRoomNumber(), r.getDescription(), r.getCapacity(), r.getPrice(), r.getType(), r.isAvailable());
    }

    public List<RoomResponse> getAll() {
        List<Room> r = roomRepo.getAll().orElseThrow(
                () -> new IllegalArgumentException("Get all rooms request resulted in error. Please try again."));
        return r.stream().map(this::buildResponse).collect(Collectors.toList());
    }

    public List<RoomResponse> getAvailable() {
        List<Room> r = roomRepo.getAllAvailableRooms().orElseThrow(
                () -> new IllegalArgumentException("Get all available rooms request resulted in error. Please try again."));
        return r.stream().map(this::buildResponse).collect(Collectors.toList());
    }

    public Integer changeAvailability(int id, Boolean isAvailable) {
        return roomRepo.changeAvailability(id, isAvailable).orElseThrow(
                () -> new IllegalArgumentException("Could not change room " + id + "'s availability to " + isAvailable + "!")
        );
    }

    public List<RoomResponse> getRoomWithCapacity(int capacity){
        List<Room> r = roomRepo.getRoomWithCapacity(capacity).orElseThrow(
                () -> new IllegalArgumentException("Get room with capacity " + capacity + " request resulted in error. Please try again."));
        return r.stream().map(this::buildResponse).collect(Collectors.toList());
    }

    public RoomResponse saveRoom(RoomRequest r){
        Room room = Room.builder()
                .roomNumber(r.getRoomNumber())
                .description(r.getDescription())
                .capacity(r.getCapacity())
                .price(r.getPrice())
                .type(r.getType())
                .isAvailable(r.isAvailable())
                .build();
        roomRepo.save(room);
        Room fromDB = roomRepo.getRoomById(room.getId()).orElseThrow(
                () -> new IllegalArgumentException("Room was not saved! Please try again."));
        log.info("The room with id {} was successfully saved!", room.getId());
        return buildResponse(fromDB);
    }

    public int deleteRoom(int id){
        Room room = roomRepo.getRoomById(id).orElseThrow(
                () -> new IllegalArgumentException("Deletion of room with id " + id + " resulted in error. Please try again."));
        roomRepo.delete(room);
        log.info("The room with id {} was successfully deleted!", room.getId());
        return room.getId();
    }

    public List<RoomResponse> getRoomByType(RoomType type){
        List<Room> r = roomRepo.getRoomsByType(type.name()).orElseThrow(
                () -> new IllegalArgumentException("Get room with type " + type.name() + " request resulted in error. Please try again.\""));
        return r.stream().map(this::buildResponse).collect(Collectors.toList());
    }

    private RoomResponse buildResponse(Room r){
        return new RoomResponse(r.getId(),
                r.getRoomNumber(),
                r.getDescription(),
                r.getCapacity(),
                r.getPrice(),
                r.getType(),
                r.isAvailable());
    }
}
