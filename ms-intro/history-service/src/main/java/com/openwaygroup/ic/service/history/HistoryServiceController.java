package com.openwaygroup.ic.service.history;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HistoryServiceController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    HistoryService historyService;

    @GetMapping("/history")
    @ResponseBody
    public List<HistoryOperation> getHistory() {
        logger.info("* <- http history request");
        List<HistoryOperation> result = historyService.getHistoryOperations();
        logger.info("* -> http history request, return " + result.size() + " items");
        return result;
    }

}
