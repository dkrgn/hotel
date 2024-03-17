package soa.roomservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import soa.roomservice.models.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepo extends JpaRepository<Room, Integer> {

    @Query(value = "SELECT * FROM rooms WHERE uid = :id", nativeQuery = true)
    Optional<Room> getRoomById(int id);

    @Query(value = "SELECT * FROM rooms", nativeQuery = true)
    Optional<List<Room>> getAll();

    @Query(value = "SELECT * FROM rooms WHERE capacity = :capacity", nativeQuery = true)
    Optional<List<Room>> getRoomWithCapacity(int capacity);

}
