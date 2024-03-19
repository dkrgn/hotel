package soa.hotelservice.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {

    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String password;

}
