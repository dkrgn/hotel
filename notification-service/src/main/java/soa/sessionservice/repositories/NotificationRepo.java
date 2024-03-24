package soa.sessionservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import soa.sessionservice.models.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationRepo extends JpaRepository<Notification, Integer> {

    @Query(value = "SELECT * FROM notifications WHERE id = :id", nativeQuery = true)
    Optional<Notification> getNotificationById(int id);

    @Query(value = "SELECT * FROM notifications", nativeQuery = true)
    Optional<List<Notification>> getAll();
}
