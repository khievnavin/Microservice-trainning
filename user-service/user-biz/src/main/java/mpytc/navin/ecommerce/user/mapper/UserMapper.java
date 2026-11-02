package mpytc.navin.ecommerce.user.mapper;


import mpytc.navin.ecommerce.user.dto.UserCreateRequest;
import mpytc.navin.ecommerce.user.dto.UserResponse;
import mpytc.navin.ecommerce.user.dto.UserUpdateRequest;
import mpytc.navin.ecommerce.user.entities.UserEntity;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    default UserResponse toResponse(UserEntity userEntity, UserRepresentation userRepresentation) {
        return UserResponse.builder()
                .id(userEntity.getId())
                .familyName(userRepresentation.getFirstName())
                .givenName(userRepresentation.getLastName())
                .address(userEntity.getAddress())
                .birthday(userEntity.getBirthday())
                .gender(userEntity.getGender())
                .mobilePhone(userEntity.getMobilePhone())
                .build();
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toEntity(UserUpdateRequest request, @MappingTarget UserEntity entity);

    UserEntity toEntity(UserCreateRequest userCreateRequest);

    UserResponse toResponse(UserEntity userEntity);

    @Mapping( source = "familyName", target = "firstName")
    @Mapping( source = "givenName", target = "lastName")
    UserRepresentation toRepresentation(UserCreateRequest userCreateRequest);

}
