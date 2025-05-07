package chnu.edu.anetrebin.notification.kafka;

import chnu.edu.anetrebin.notification.kafka.messages.TransactionMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(
            topics = "test",
            groupId = "notification-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(String message) {
        System.out.println("Received string message: " + message);
    }

    @KafkaListener(
            topics = "object",
            groupId = "notification-group",
            containerFactory = "transactionKafkaListenerContainerFactory"
    )
    public void listen(TransactionMessage message) {
        System.out.println("Received transaction message: " + message);
    }
}