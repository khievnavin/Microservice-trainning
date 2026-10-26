package mpytc.navin.ecommerce.user.service.impl;

import com.mpytc.navin.order.restapi.exception.GlobalException;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mpytc.navin.ecommerce.user.dto.UserCreateRequest;
import mpytc.navin.ecommerce.user.dto.UserResponse;
import mpytc.navin.ecommerce.user.entities.UserEntity;
import mpytc.navin.ecommerce.user.exception.PasswordMismatchException;
import mpytc.navin.ecommerce.user.exception.UsernameConflictException;
import mpytc.navin.ecommerce.user.mapper.UserMapper;
import mpytc.navin.ecommerce.user.repository.UserRepository;
import mpytc.navin.ecommerce.user.service.UserService;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final Keycloak keycloak;

    @Override
    public UserResponse create(UserCreateRequest userCreateRequest) {
        UserEntity userEntity = userMapper.toEntity(userCreateRequest);

        //validate username
        if (userRepository.existsByUsername(userCreateRequest.username())){
            throw new UsernameConflictException("Username alredy exit",
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

         try (Response response = keycloak.realm("ecommerce")
                 .users().create(userRepresentation);) {
             log.info("Keycloak response: {}", response.getStatus());

             if (response.getStatus() == HttpStatus.CREATED.value()){
                 log.info("User created successfully");
                 // use the Keycloak user id as the database id
                 userEntity.setId(CreatedResponseUtil.getCreatedId(response));
             }else if (response.getStatus() == HttpStatus.CONFLICT.value()){
                 throw new UsernameConflictException("Username already exist in Keycloak",
                         HttpStatus.CONFLICT);
             }else {
                 throw new GlobalException("User creation failed in Keycloak",
                         HttpStatus.INTERNAL_SERVER_ERROR);
             }
         }

        userEntity = userRepository.save(userEntity);

        return userMapper.toResponse(userEntity);
    }
}
