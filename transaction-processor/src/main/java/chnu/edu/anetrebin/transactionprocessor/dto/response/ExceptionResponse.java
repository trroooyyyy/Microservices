package chnu.edu.anetrebin.transactionprocessor.dto.response;

import java.time.LocalDateTime;

public record ExceptionResponse(
        LocalDateTime time,
        String message
) {}
