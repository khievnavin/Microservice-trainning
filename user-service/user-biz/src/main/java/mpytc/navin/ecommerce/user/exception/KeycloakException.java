package mpytc.navin.ecommerce.user.exception;

import com.mpytc.navin.order.restapi.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class KeycloakException extends GlobalException {
    public KeycloakException(HttpStatus status, String message) {
        super(message,status);

    }
}
