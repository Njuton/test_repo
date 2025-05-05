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
    private final DataSourceRegistryInfo registryInfo;

    public TransactionRoutingDataSource(ReadOnlyKeyRegistry readOnlyKeyRegistry, DataSourceRegistryInfo registryInfo) {
        this.readOnlyKeyRegistry = readOnlyKeyRegistry;
        this.registryInfo = registryInfo;
    }

    //метод возвращает ключ, под которым находится конкретный датасорс
    @Override
    protected Object determineCurrentLookupKey() {
        TransactionContextHolder.TransactionType transactionType = TransactionContextHolder.getTransactionType();
        // распределяем нагрузку рандомно на любой из слэйвов (если есть DS)
        if (transactionType == TransactionContextHolder.TransactionType.READ_ONLY && !readOnlyKeyRegistry.isEmpty()) {
            String key = readOnlyKeyRegistry.getRandomKey();
            logger.info("Use key={} for transaction", key);
            return key;
        }

        //  если пришел шардинг - отправляет на DS шардинга, если он есть
        if (transactionType == TransactionContextHolder.TransactionType.SHARDING && registryInfo.getShardingDSEnabled().get()) {
            logger.info("Use sharding DS for transaction");
            return TransactionContextHolder.TransactionType.SHARDING;
        }

        logger.info("Use read-write DS for transaction");
        return TransactionContextHolder.TransactionType.READ_WRITE;
    }
}
