package mpytc.navin.ecommerce.user.dto;

import java.util.Date;

public record UserResponse(
        String id,
        String familyName,
        String giveName,
        String gender,
        Date birthday,
        String mobilePhone,
        String address

) {
}
