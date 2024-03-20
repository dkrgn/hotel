package soa.hotelservice.auth.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.hotelservice.auth.dto.RegistrationRequest;
import soa.hotelservice.auth.dto.RegistrationResponse;
import soa.hotelservice.auth.service.RegistrationService;

@RestController
@RequestMapping(path = "/register.html/")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<RegistrationResponse> registerUser(
            @Valid @RequestBody RegistrationRequest request
    ) {
        return ResponseEntity.ok(registrationService.register(request));
    }
}
