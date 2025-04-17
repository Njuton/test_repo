package com.example.myapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rmartynov
 * @since 27.03.2025
 */
@Configuration
public class DataSourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Bean
    @ConfigurationProperties("spring.datasource.read-only")
    public DataSourceProperties readOnlyDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.read-write")
    public DataSourceProperties readWriteDataSourceProperties() {
        return new DataSourceProperties();
    }

    private DataSource getReadOnlyDataSource() {
        DataSourceProperties properties = readOnlyDataSourceProperties();
        return properties.getUrl() != null ? properties.initializeDataSourceBuilder().build() : null;
    }

    private DataSource getReadWriteDataSource() {
        return readWriteDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public DataSource routingDataSource() throws SQLException {
        AbstractRoutingDataSource dataSource = new TransactionRoutingDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();

        DataSource readOnlyDataSource = getReadOnlyDataSource();
        DataSource readWriteDataSource = getReadWriteDataSource();

        if (readOnlyDataSource != null) {
            logger.info("Use read-only datasource: {}", readOnlyDataSource.getConnection().getMetaData().getURL());
            targetDataSources.put(TransactionContextHolder.TransactionType.READ_ONLY, readOnlyDataSource);
        }

        logger.info("Use write datasource: {}", readWriteDataSource.getConnection().getMetaData().getURL());
        targetDataSources.put(TransactionContextHolder.TransactionType.READ_WRITE, readWriteDataSource);
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(readWriteDataSource);
        return dataSource;
    }
}
