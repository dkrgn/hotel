package soa.paymentservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import soa.paymentservice.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentRepo extends JpaRepository<Payment, Integer> {

    @Query(value = "SELECT * FROM payments WHERE id = :id", nativeQuery = true)
    Optional<Payment> getPaymentById(int id);

    @Query(value = "DELETE FROM payments WHERE room_id = :id", nativeQuery = true)
    @Transactional
    @Modifying
    Optional<Integer> deletePaymentsByRoomId(int id);

}
