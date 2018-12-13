package com.openwaygroup.ic.service.history;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistoryRepository extends MongoRepository<HistoryOperation, Long> {
}
