package com.openwaygroup.ic.service.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class PaymentServiceController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendToTopic(@RequestBody Payment payment) throws JsonProcessingException {
        paymentService.process(payment);
    }


}
