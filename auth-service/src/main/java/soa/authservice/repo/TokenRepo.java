package soa.authservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import soa.authservice.model.Token;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepo extends JpaRepository<Token, Integer> {

    @Query(value = "SELECT * FROM tokens WHERE id = :id", nativeQuery = true)
    Optional<Token> getTokenById(int id);

    @Query(value = "SELECT * FROM tokens WHERE NOW() >= expires_at", nativeQuery = true)
    @Modifying
    @Transactional
    Optional<List<Token>> clearExpiredTokens();
}
