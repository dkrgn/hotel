package soa.authservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.authservice.dto.token.TokenRequest;
import soa.authservice.dto.token.TokenResponse;
import soa.authservice.service.TokenService;

@RestController
@RequestMapping("/token")
@AllArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @DeleteMapping("/all")
    public ResponseEntity<Integer> deleteAllExpired() {
        return ResponseEntity.ok(tokenService.clearExpiredTokens());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteTokenById(@PathVariable("id") int id) {
        System.out.println("TOKEN ARRIVED");
        return ResponseEntity.ok(tokenService.deleteTokenById(id));
    }
}
