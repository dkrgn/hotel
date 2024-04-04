package soa.authservice.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import soa.authservice.dto.token.TokenResponse;
import soa.authservice.model.Token;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private boolean canLogin;
    private String email;
    private TokenResponse tokenResponse;
}
