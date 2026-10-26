package mpytc.navin.ecommerce.user.dto;

import jakarta.validation.constraints.NotBlank;

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
    String roleId // user (, as delimiter)
) {


}
