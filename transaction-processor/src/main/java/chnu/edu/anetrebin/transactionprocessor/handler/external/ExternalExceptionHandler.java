package chnu.edu.anetrebin.transactionprocessor.handler.external;

import chnu.edu.anetrebin.transactionprocessor.exceptions.external.AccountNotFoundException;
import chnu.edu.anetrebin.transactionprocessor.exceptions.external.CurrencyServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static chnu.edu.anetrebin.transactionprocessor.handler.ResponseBuilder.constructResponseEntity;

@RestControllerAdvice
public class ExternalExceptionHandler {
    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleAccountNotFoundException(AccountNotFoundException e) {
        return constructResponseEntity(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(CurrencyServiceException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleCurrencyServiceException(CurrencyServiceException ex) {
        return constructResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
