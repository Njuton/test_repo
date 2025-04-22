package com.example.myapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Основной класс, который на основании контекста транзакции определяет какой датасорс будет использован
 *
 * @author rmartynov
 * @since 20.04.2025
 */
public class TransactionRoutingDataSource extends AbstractRoutingDataSource {
    private static final Logger logger = LoggerFactory.getLogger(TransactionRoutingDataSource.class);
    private final ReadOnlyKeyRegistry readOnlyKeyRegistry;

    public TransactionRoutingDataSource(ReadOnlyKeyRegistry readOnlyKeyRegistry) {
        this.readOnlyKeyRegistry = readOnlyKeyRegistry;
    }

    //метод возвращает ключ, под которым находится конкретный датасорс
    @Override
    protected Object determineCurrentLookupKey() {
        TransactionContextHolder.TransactionType transactionType = TransactionContextHolder.getTransactionType();
        if (transactionType != null) {
            logger.info("Use {} transaction for current db query", transactionType.name());
        }
        // распределяем нагрузку рандомно на любой из слэйвов
        if (transactionType == TransactionContextHolder.TransactionType.READ_ONLY && !readOnlyKeyRegistry.isEmpty()) {
            String key = readOnlyKeyRegistry.getRandomKey();
            logger.info("Use key={} for transaction", key);
            return key;
        }
        return TransactionContextHolder.TransactionType.READ_WRITE;
    }
}
