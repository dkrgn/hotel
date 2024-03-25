package soa.bookingservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import soa.bookingservice.model.Booking;

import java.util.Optional;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {

    @Query(value = "SELECT * FROM bookings WHERE id = :id", nativeQuery = true)
    Optional<Booking> getBookingById(int id);
}
