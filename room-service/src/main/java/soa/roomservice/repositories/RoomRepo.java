package soa.roomservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import soa.roomservice.models.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepo extends JpaRepository<Room, Integer> {

    @Query(value = "SELECT * FROM rooms WHERE id = :id", nativeQuery = true)
    Optional<Room> getRoomById(int id);

    @Query(value = "SELECT * FROM rooms", nativeQuery = true)
    Optional<List<Room>> getAll();

    @Query(value = "SELECT * FROM rooms WHERE capacity = :capacity", nativeQuery = true)
    Optional<List<Room>> getRoomWithCapacity(int capacity);

    @Query(value = "SELECT * FROM rooms WHERE cost = :cost", nativeQuery = true)
    Optional<List<Room>> findRoomsByCost(int cost);

    @Query(value = "SELECT * FROM rooms WHERE type = :type", nativeQuery = true)
    Optional<List<Room>> getRoomsByType(String type);
}
