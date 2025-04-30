package chnu.edu.anetrebin.transactionprocessor.handler;

import chnu.edu.anetrebin.transactionprocessor.exceptions.TransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static chnu.edu.anetrebin.transactionprocessor.handler.ResponseBuilder.constructResponseEntity;

@RestControllerAdvice
public class TransactionExceptionHandler {
    @ExceptionHandler(TransactionException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handle(TransactionException ex) {
        return constructResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
