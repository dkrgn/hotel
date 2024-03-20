package soa.hotelservice.auth.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.hotelservice.auth.dto.LoginRequest;
import soa.hotelservice.auth.dto.LoginResponse;
import soa.hotelservice.auth.service.LoginService;
import soa.hotelservice.dto.user.UserResponse;
import soa.hotelservice.service.HotelUserService;

@RestController
@RequestMapping(path = "/login.html/")
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
