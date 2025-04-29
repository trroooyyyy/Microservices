package chnu.edu.anetrebin.currencyexchange.dto.response;

import java.time.LocalDateTime;

public record ExceptionResponse(
        LocalDateTime time,
        String message
) {}
