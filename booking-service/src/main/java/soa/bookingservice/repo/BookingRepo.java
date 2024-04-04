package soa.bookingservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import soa.bookingservice.model.Booking;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {

    @Query(value = "SELECT * FROM bookings WHERE id = :id", nativeQuery = true)
    Optional<Booking> getBookingById(int id);

    @Query(value = "SELECT * FROM bookings WHERE user_id = :id", nativeQuery = true)
    @Modifying
    @Transactional
    Optional<List<Booking>> getBookingsByUserId(int id);
}
