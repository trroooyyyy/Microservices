package chnu.edu.anetrebin.notification.dto.response;

import java.time.LocalDateTime;

public record ExceptionResponse(
        LocalDateTime time,
        String message
) {}
