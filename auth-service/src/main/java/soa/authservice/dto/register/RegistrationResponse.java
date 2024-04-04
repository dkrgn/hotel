package soa.authservice.dto.register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import soa.authservice.dto.token.TokenResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponse {
    private boolean isSaved;
    private String email;
    private TokenResponse tokenResponse;
}
