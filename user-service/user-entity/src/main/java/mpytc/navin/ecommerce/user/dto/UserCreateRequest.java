package mpytc.navin.ecommerce.user.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.Date;

public record UserCreateRequest(

        @NotBlank
    String username,
        @NotBlank
    String email,
        @NotBlank
    String familyName,
        @NotBlank
    String givenName,
        @NotBlank
    String password,
        @NotBlank
    String confirmPassword,
        @NotBlank
    String roleName // user (, as delimiter)
) {


}
