package mpytc.navin.ecommerce.user.service.impl;

import com.mpytc.navin.order.restapi.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mpytc.navin.ecommerce.user.dto.UserCreateRequest;
import mpytc.navin.ecommerce.user.dto.UserResponse;
import mpytc.navin.ecommerce.user.dto.UserUpdateRequest;
import mpytc.navin.ecommerce.user.entities.UserEntity;
import mpytc.navin.ecommerce.user.exception.PasswordMismatchException;
import mpytc.navin.ecommerce.user.exception.UserHybridDataInconsistencyException;
import mpytc.navin.ecommerce.user.exception.UserNotFoundException;
import mpytc.navin.ecommerce.user.exception.UsernameConflictException;
import mpytc.navin.ecommerce.user.mapper.UserMapper;
import mpytc.navin.ecommerce.user.repository.UserRepository;
import mpytc.navin.ecommerce.user.service.KeycloakRoleService;
import mpytc.navin.ecommerce.user.service.KeycloakUserService;
import mpytc.navin.ecommerce.user.service.UserService;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final KeycloakUserService keycloakUserService;
    private final KeycloakRoleService keycloakRoleService;

    @Override
    public UserResponse create(UserCreateRequest userCreateRequest) {
        UserEntity userEntity = userMapper.toEntity(userCreateRequest);

        //validate username
        if (userRepository.existsByUsername(userCreateRequest.username())){
            throw new UsernameConflictException("Username already exit",
                    HttpStatus.CONFLICT);
        }

        //validate password
        if (!userCreateRequest.password().equals(userCreateRequest.confirmPassword())){
            throw new PasswordMismatchException("Password does not match",
                    HttpStatus.BAD_REQUEST);
        }

        // save user into Keycloak
        //After saved user, save user into database
        UserRepresentation userRepresentation = userMapper.toRepresentation(userCreateRequest);
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(false);


        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(userCreateRequest.password());

        userRepresentation.setCredentials(List.of(credentialRepresentation));


        keycloakUserService.createKeycloakUser(userRepresentation);
        UserRepresentation createUser = keycloakUserService.getKeycloakUserByUsername(
                userEntity.getUsername()
        );
        log.info("User created: {}", createUser);
        keycloakRoleService.assignRoles(createUser.getId(), userCreateRequest.roleName());

        //Saving user to Database
        userEntity.setId(createUser.getId());
        userRepository.save(userEntity);

        return userMapper.toResponse(userEntity, createUser);
    }


    @Override
    public UserResponse update(String id, UserUpdateRequest userUpdateRequest){

        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(
                        "User not Found", HttpStatus.NOT_FOUND
                                ));
        userMapper.toEntity(userUpdateRequest, userEntity);
        userRepository.save(userEntity);

        UserRepresentation userRepresentation = keycloakUserService.getKeycloakUserByUsername(userEntity.getUsername());

        //Attempt to keycloak data
        if (!(userUpdateRequest.familyName() == null && userUpdateRequest.givenName() == null)){
            if(userUpdateRequest.familyName() != null){
                userRepresentation.setFirstName(userUpdateRequest.familyName());
            }
            if(userUpdateRequest.givenName() != null){
                userRepresentation.setLastName(userUpdateRequest.givenName());
            }
            keycloakUserService.updateKeycloakUser(id, userRepresentation);
        }
        return userMapper.toResponse(userEntity, userRepresentation);
    }

    @Override
    public UserResponse getById(String id){
        UserEntity userEntity = userRepository
                .findById(id)
                .orElseThrow(()-> new UserNotFoundException(
                        "User not Found", HttpStatus.NOT_FOUND
                ));
        UserRepresentation userRepresentation = keycloakUserService.getKeycloakUserById(id);
        return  userMapper.toResponse(userEntity, userRepresentation);
    }

    @Override
    public UserResponse getByUsername(String username) {
        UserEntity userEntity = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(
                        "User not found",
                        HttpStatus.NOT_FOUND
                                ));
        UserRepresentation userRepresentation = keycloakUserService.getKeycloakUserByUsername(username);

        return userMapper.toResponse(
                userEntity,
                userRepresentation
        );
    }

    @Override
    public PageResponse<UserResponse> getUsersByRoleName(String roleName, Pageable pageable) {
        Slice<UserRepresentation> userRepresentationSlice = keycloakUserService.getKeycloakUsersByRoleName(roleName, pageable);
        return mergeUserEntityWithUserRepresentation(userRepresentationSlice);
    }

    @Override
    public PageResponse<UserResponse> getUsers(Pageable pageable) {
       Slice<UserRepresentation> userRepresentationSlice = keycloakUserService.getKeycloakUsers(pageable);
        return mergeUserEntityWithUserRepresentation(userRepresentationSlice);
    }

    @Override
    public void deleteById(String id) {
        UserEntity userEntity = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "User not found",
                        HttpStatus.NOT_FOUND
                                ));
        userRepository.delete(userEntity);
        keycloakUserService.deleteKeycloakUserById(id);
    }


    private PageResponse<UserResponse> mergeUserEntityWithUserRepresentation(Slice<UserRepresentation> userRepresentationSlice) {
        List<UserResponse> userResponseList = userRepresentationSlice
                .map(userRepresentation -> {
                    UserEntity userEntity = userRepository.findById(userRepresentation.getId())
                            .orElseThrow(() -> new UserHybridDataInconsistencyException("User hybrid data is inconsistent",
                                    HttpStatus.INTERNAL_SERVER_ERROR));
                    return userMapper.toResponse(userEntity, userRepresentation);
                })
                .toList();
        return PageResponse.<UserResponse>builder()
                .items(userResponseList)
                .page(userRepresentationSlice.getNumber())
                .size(userRepresentationSlice.getSize())
                .hasNext(userRepresentationSlice.hasNext())
                .build();
    }

}
