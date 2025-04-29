package chnu.edu.anetrebin.currencyexchange.handler;

import chnu.edu.anetrebin.currencyexchange.exceptions.CurrencyExchangeException;
import chnu.edu.anetrebin.currencyexchange.exceptions.CurrencyExchangeNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static chnu.edu.anetrebin.currencyexchange.handler.ResponseBuilder.constructResponseEntity;

@RestControllerAdvice
public class CurrencyExchangeExceptionHandler {
    @ExceptionHandler(CurrencyExchangeException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    protected ResponseEntity<Object> handleException(CurrencyExchangeException e) {
        return constructResponseEntity(HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(CurrencyExchangeNotFound.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleException(CurrencyExchangeNotFound e) {
        return constructResponseEntity(HttpStatus.NOT_FOUND, e.getMessage());
    }
}
