package soa.roomservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import soa.roomservice.models.Room;
import soa.roomservice.repositories.RoomRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {
    private final RoomRepo roomRepo;

    public Room getRoomById(int id){return roomRepo.getRoomById(id).orElse(new Room());}

    public List<Room> getAll() {
        return roomRepo.getAll().orElse(new ArrayList<>());
    }

    public List<Room> getRoomWithCapacity(int capacity){
        return roomRepo.getRoomWithCapacity(capacity).orElse(new ArrayList<>());
    }

    public Room saveRoom(int id, String roomNumber, String description, int capacity){
        Room room = new Room(id, roomNumber, description, capacity);
        return roomRepo.save(room);
    }

    public int deleteRoom(int id){
        Room room = roomRepo.getRoomById(id).orElse(null);
        if (room == null){
            return -1;
        }
        roomRepo.delete(room);
        return room.getId();
    }
}
