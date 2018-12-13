package com.openwaygroup.ic.service.history;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.lang.String.format;

@Controller
public class HistoryServiceController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    HistoryService historyService;

    @Autowired
    RestTemplate restTemplate;

    @Value("${server.port}")
    String port;

    @GetMapping("/history")
    @ResponseBody
    public List<HistoryOperation> getHistory() {
        logger.info("* <- http history request");
        List<HistoryOperation> result = historyService.getHistoryOperations();
        logger.info("* -> http history request, return " + result.size() + " items");
        return result;
    }

    @GetMapping("/chaining")
    @ResponseBody
    public String chaining() {
        String url = format("http://localhost:%s/history", port);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return "Chaining + " + response.getBody();
    }
}
