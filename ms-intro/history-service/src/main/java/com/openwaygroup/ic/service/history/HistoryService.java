package com.openwaygroup.ic.service.history;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openwaygroup.ic.service.payment.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
public class HistoryService {

    @Autowired
    HistoryRepository historyRepository;

    @KafkaListener(topics = "payment-created")
    public void newPayment(@Payload String payload) throws IOException {
        System.out.println("Receive message: " + payload);

        Payment payment = toPayment(payload);

        HistoryOperation operation = createHistoryOperation(payment);

        historyRepository.save(operation);
    }

    private HistoryOperation createHistoryOperation(Payment payment) {
        BigDecimal fee = BigDecimal.TEN;

        HistoryOperation operation = new HistoryOperation();
        operation.setId(UUID.randomUUID().toString());
        operation.setPayment(payment);
        operation.setFee(fee);
        operation.setTotalAmount(payment.getAmount().add(fee));
        operation.setBonus(BigDecimal.valueOf(15));
        return operation;
    }

    private Payment toPayment(@Payload String payload) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(payload, Payment.class);
    }

    public List<HistoryOperation> getHistoryOperations() {
        return historyRepository.findAll();
    }

}
