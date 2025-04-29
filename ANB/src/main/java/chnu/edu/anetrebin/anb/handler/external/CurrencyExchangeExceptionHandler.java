package chnu.edu.anetrebin.anb.handler.external;

import chnu.edu.anetrebin.anb.exceptions.external.CurrencyServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static chnu.edu.anetrebin.anb.handler.ResponseBuilder.constructResponseEntity;

@RestControllerAdvice
public class CurrencyExchangeExceptionHandler {
    @ExceptionHandler(CurrencyServiceException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleCurrencyServiceException(CurrencyServiceException ex) {
        return constructResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
