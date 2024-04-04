package soa.authservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import soa.authservice.dto.token.TokenRequest;
import soa.authservice.dto.token.TokenResponse;
import soa.authservice.model.Token;
import soa.authservice.repo.TokenRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class TokenService {

    private final TokenRepo tokenRepo;

    public TokenResponse saveToken(TokenRequest request) {
        Token token = buildToken(request.getUserId());
        tokenRepo.save(token);
        Token fromDB = tokenRepo.getTokenById(token.getId()).orElseThrow(
                () -> new IllegalArgumentException("Token could nt be saved!")
        );
        log.info("The token with id {} was successfully saved in the database!", fromDB.getId());
        return new TokenResponse(fromDB.getId(), fromDB.getUserId());
    }

    public Token buildToken(int userId) {
        LocalDateTime cur = LocalDateTime.now();
        String token = UUID.randomUUID().toString();
        return Token.builder()
                .token(token)
                .createdAt(cur)
                .expiresAt(cur.plusHours(1))
                .userId(userId)
                .build();
    }

    public Integer clearExpiredTokens() {
        List<Token> tokens = tokenRepo.clearExpiredTokens().orElseThrow(
                () -> new IllegalArgumentException("Token could not be cleared! Error occurred!")
        );
        tokenRepo.deleteAll(tokens);
        return tokens.size();
    }


    public Integer deleteTokenById(int id) {
        Token token = tokenRepo.getTokenById(id).orElseThrow(
                () -> new IllegalArgumentException("Token could not be found!")
        );
        tokenRepo.delete(token);
        log.info("The token with id {} was successfully deleted!", token.getId());
        return token.getId();
    }
}
