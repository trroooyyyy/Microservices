package chnu.edu.anetrebin.currencyexchange.handler;

import chnu.edu.anetrebin.currencyexchange.dto.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public final class ResponseBuilder {
    public static ResponseEntity<Object> constructResponseEntity(HttpStatus status, String message) {
        ExceptionResponse response = new ExceptionResponse(
                LocalDateTime.now(),
                message
        );

        return ResponseEntity.status(status).body(response);
    }
}
