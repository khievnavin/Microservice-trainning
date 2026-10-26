package mpytc.navin.ecommerce.user.exception;

import com.mpytc.navin.order.restapi.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class PasswordMismatchException extends GlobalException {

    public PasswordMismatchException(String message, HttpStatus status) {
        super(message, status);
    }
}
