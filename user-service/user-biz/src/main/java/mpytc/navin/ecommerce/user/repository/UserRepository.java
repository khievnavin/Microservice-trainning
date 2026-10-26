package mpytc.navin.ecommerce.user.repository;

import mpytc.navin.ecommerce.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<UserEntity, String> {


    boolean existsByUsername(String username);

}
