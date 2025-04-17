package com.example.myapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class TransactionRoutingDataSource extends AbstractRoutingDataSource {
    private static final Logger logger = LoggerFactory.getLogger(TransactionRoutingDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        TransactionContextHolder.TransactionType transactionType = TransactionContextHolder.getTransactionType();
        if (transactionType != null){
            logger.info("Use {} transaction for current db query", transactionType.name());
        }
        return transactionType != null ? transactionType : TransactionContextHolder.TransactionType.READ_WRITE;
    }
}
