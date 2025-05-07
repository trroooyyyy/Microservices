package chnu.edu.anetrebin.transactionprocessor.kafka;

import chnu.edu.anetrebin.transactionprocessor.kafka.messages.TransactionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, TransactionMessage> transactionMessageKafkaTemplate;

    public void sendMessage(String topicName, String message) {
        kafkaTemplate.send(topicName, message);
    }

    public void sendObject(TransactionMessage transactionMessage) {
        transactionMessageKafkaTemplate.send(transactionMessage.topic(), transactionMessage);
    }
}

