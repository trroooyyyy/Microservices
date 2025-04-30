package chnu.edu.anetrebin.notification.handler;

import chnu.edu.anetrebin.notification.exceptions.NotificationNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static chnu.edu.anetrebin.notification.handler.ResponseBuilder.constructResponseEntity;

@RestControllerAdvice
public class NotificationExceptionHandler {
    @ExceptionHandler(NotificationNotFound.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleException(NotificationNotFound e) {
        return constructResponseEntity(HttpStatus.NOT_FOUND, e.getMessage());
    }
}
