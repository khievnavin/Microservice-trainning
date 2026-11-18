package mpytc.navin.ecommerce.user.service;

import com.mpytc.navin.core.PageResponse;
import jakarta.validation.Valid;
import mpytc.navin.ecommerce.user.dto.UserCreateRequest;
import mpytc.navin.ecommerce.user.dto.UserResponse;
import mpytc.navin.ecommerce.user.dto.UserUpdateRequest;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserResponse create(@Valid UserCreateRequest userCreateRequest);

    UserResponse update(String id, UserUpdateRequest userUpdateRequest);

    UserResponse getById(String id);

    UserResponse getByUsername(String username);

    PageResponse<UserResponse> getUsersByRoleName(String roleName, Pageable pageable);

    PageResponse<UserResponse> getUsers(Pageable pageable);

    void deleteById(String id);
}
