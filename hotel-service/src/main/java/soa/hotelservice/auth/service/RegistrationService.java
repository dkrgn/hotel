package soa.hotelservice.auth.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import soa.hotelservice.auth.dto.RegistrationRequest;
import soa.hotelservice.auth.dto.RegistrationResponse;
import soa.hotelservice.dto.user.UserRequest;
import soa.hotelservice.dto.user.UserResponse;
import soa.hotelservice.service.HotelUserService;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final HotelUserService hotelUserService;
    private final EmailValidatorService validator;

    public RegistrationResponse register(RegistrationRequest request) {
        boolean isValidEmail = validator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("Provided email is invalid " + request.getEmail());
        }
        UserResponse response = hotelUserService.saveUser(new UserRequest(
                request.getFirstName(),
                request.getLastName(),
                request.getMobileNumber(),
                request.getEmail(),
                PasswordEncoder.getPasswordEncoded(request.getPassword()))
        );
        if (response == null) {
            throw new IllegalArgumentException("The user could not be saved!");
        } else {
            return new RegistrationResponse(true);
        }
    }
}
