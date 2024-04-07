package soa.roomservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import soa.roomservice.models.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepo extends JpaRepository<Room, Integer> {

    @Query(value = "SELECT * FROM rooms WHERE id = :id", nativeQuery = true)
    Optional<Room> getRoomById(int id);

    @Query(value = "SELECT * FROM rooms", nativeQuery = true)
    Optional<List<Room>> getAll();

    @Query(value = "SELECT * FROM rooms WHERE is_available", nativeQuery = true)
    Optional<List<Room>> getAllAvailableRooms();

    @Query(value = "UPDATE rooms SET is_available = :isAvailable WHERE id = :id", nativeQuery = true)
    @Modifying
    @Transactional
    Optional<Integer> changeAvailability(int id, boolean isAvailable);
}
