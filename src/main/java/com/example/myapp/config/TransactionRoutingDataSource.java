package com.example.myapp.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class TransactionRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        TransactionContextHolder.TransactionType transactionType = TransactionContextHolder.getTransactionType();
        return transactionType != null ? transactionType : TransactionContextHolder.TransactionType.READ_WRITE;
    }
}
