package chnu.edu.anetrebin.notification.dto.request;

import chnu.edu.anetrebin.notification.enums.NotificationChannel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record NotificationRequest(
        @NotNull(message = "User ID cannot be null")
        @Positive(message = "User ID must be a positive number")
        Long userId,

        @NotBlank(message = "Message cannot be blank")
        @NotNull(message = "Message cannot be null")
        @Size(min= 1, max = 200, message = "Message should be from 1 to 200 characters")
        String message,

        @NotNull(message = "Channel cannot be null")
        NotificationChannel channel) {
}
