package soa.hotelservice.dto.user;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String password;

}
