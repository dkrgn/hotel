package soa.authservice.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.authservice.dto.login.LoginRequest;
import soa.authservice.dto.login.LoginResponse;
import soa.authservice.service.LoginService;

@RestController
@RequestMapping(path = "/login")
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<LoginResponse> checkCredentials(
            @Valid @RequestBody LoginRequest request
            ) {
        return ResponseEntity.ok(loginService.checkCredentials(request));
    }

}
