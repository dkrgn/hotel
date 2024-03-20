package soa.hotelservice.auth.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import soa.hotelservice.auth.dto.LoginRequest;
import soa.hotelservice.auth.dto.LoginResponse;
import soa.hotelservice.dto.user.UserResponse;
import soa.hotelservice.service.HotelUserService;

@Service
@AllArgsConstructor
public class LoginService {

    private final HotelUserService hotelUserService;

    public LoginResponse checkCredentials(LoginRequest request) {
        UserResponse response = hotelUserService.getUserByEmail(request.getEmail());
        if (response == null) {
            throw new IllegalArgumentException("User Service could not find user with email " + request.getEmail() + "!");
        }
        String passwordEncoded = PasswordEncoder.getPasswordEncoded(request.getPassword());
        if (!response.getPassword().equals(passwordEncoded)) {
            throw new IllegalStateException("wrong password");
        }
        return new LoginResponse(true);
    }
}
