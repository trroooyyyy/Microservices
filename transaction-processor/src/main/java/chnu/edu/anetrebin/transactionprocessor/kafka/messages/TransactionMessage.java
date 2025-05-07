package chnu.edu.anetrebin.transactionprocessor.kafka.messages;

import java.math.BigDecimal;

public record TransactionMessage(
        String topic,
        String message,
        BigDecimal amount) {
}
