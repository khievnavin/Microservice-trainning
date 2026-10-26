package mpytc.navin.ecommerce.user.service;

import jakarta.validation.Valid;
import mpytc.navin.ecommerce.user.dto.UserCreateRequest;
import mpytc.navin.ecommerce.user.dto.UserResponse;

public interface UserService {

    UserResponse create(@Valid UserCreateRequest userCreateRequest);

}
