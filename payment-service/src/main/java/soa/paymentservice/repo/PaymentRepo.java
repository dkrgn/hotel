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

    @Query(value = "SELECT * FROM payments", nativeQuery = true)
    Optional<List<Payment>> getAll();

    @Query(value = "DELETE FROM payments WHERE user_id = :id", nativeQuery = true)
    @Transactional
    @Modifying
    Optional<Integer> deletePaymentsByUserId(int id);

}
