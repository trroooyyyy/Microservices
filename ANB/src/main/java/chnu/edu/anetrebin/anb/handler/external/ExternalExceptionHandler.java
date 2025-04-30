package chnu.edu.anetrebin.anb.handler.external;

import chnu.edu.anetrebin.anb.exceptions.external.CurrencyServiceException;
import chnu.edu.anetrebin.anb.exceptions.external.NotificationException;
import chnu.edu.anetrebin.anb.exceptions.external.TransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static chnu.edu.anetrebin.anb.handler.ResponseBuilder.constructResponseEntity;

@RestControllerAdvice
public class ExternalExceptionHandler {
    @ExceptionHandler(CurrencyServiceException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleCurrencyServiceException(CurrencyServiceException ex) {
        return constructResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(NotificationException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    protected ResponseEntity<Object> handleNotificationException(NotificationException ex) {
        return constructResponseEntity(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(TransactionException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleTransactionException(TransactionException ex) {
        return constructResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
