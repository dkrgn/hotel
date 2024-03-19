package soa.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import soa.userservice.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM users WHERE id = :id", nativeQuery = true)
    Optional<User> getUserById(int id);

    @Query(value = "SELECT * FROM users", nativeQuery = true)
    Optional<List<User>> getAll();
}
