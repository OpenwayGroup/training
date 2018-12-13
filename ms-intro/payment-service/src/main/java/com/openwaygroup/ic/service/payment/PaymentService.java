package com.openwaygroup.ic.service.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PaymentService {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void process(Payment payment) throws JsonProcessingException {
        processPayment(payment);
        Message<String> message = createMessage(payment);
        sendMessage(message);
    }

    private void processPayment(Payment payment) {
        payment.setDate(new Date());
    }

    private Message<String> createMessage(Payment payment) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String payload = objectMapper.writeValueAsString(payment);

        return MessageBuilder
                .withPayload(payload)
                .setHeader(KafkaHeaders.TOPIC, "payment-created")
                .build();
    }

    private void sendMessage(Message<String> message) {
        try {
            kafkaTemplate.send(message).get();
            logger.info("Send message: " + message.getPayload());
        } catch (Exception e) {
            logger.error("Failed to send message", e);
        }
    }

}
