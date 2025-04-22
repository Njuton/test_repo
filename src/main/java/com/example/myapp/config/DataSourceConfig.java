package com.example.myapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rmartynov
 * @since 27.03.2025
 */
@Configuration
@EnableConfigurationProperties(ReadOnlyProperties.class)
public class DataSourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
    private final ReadOnlyKeyRegistry readOnlyKeyRegistry;

    public DataSourceConfig(ReadOnlyKeyRegistry readOnlyKeyRegistry) {
        this.readOnlyKeyRegistry = readOnlyKeyRegistry;
    }

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

    @Bean
    public List<DataSource> readOnlyDataSources(ReadOnlyProperties properties) {
        List<DataSource> dataSources = new ArrayList<>();
        for (ReadOnlyProperties.ReadOnlyDataSourceProperty property : properties.getReadOnly()) {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setUrl(property.getUrl());
            dataSource.setUsername(property.getUsername());
            dataSource.setPassword(property.getPassword());
            dataSource.setDriverClassName(property.getDriverClassName());
            dataSources.add(dataSource);
        }
        return dataSources;
    }

    private DataSource getReadWriteDataSource() {
        return readWriteDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public DataSource routingDataSource(List<DataSource> readOnlyDataSources) throws SQLException {
        AbstractRoutingDataSource dataSource = new TransactionRoutingDataSource(readOnlyKeyRegistry);
        Map<Object, Object> targetDataSources = new HashMap<>();

        DataSource readWriteDataSource = getReadWriteDataSource();

        if (!readOnlyDataSources.isEmpty()) {
            int i = 0;
            for (DataSource rds : readOnlyDataSources) {
                logger.info("Use read-only datasource: {}", rds.getConnection().getMetaData().getURL());
                String genKey = TransactionContextHolder.TransactionType.READ_ONLY.name() + i;
                readOnlyKeyRegistry.addKey(genKey);
                targetDataSources.put(genKey, rds);
                i++;
            }
        }

        logger.info("Use write datasource: {}", readWriteDataSource.getConnection().getMetaData().getURL());
        targetDataSources.put(TransactionContextHolder.TransactionType.READ_WRITE, readWriteDataSource);
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(readWriteDataSource);
        return dataSource;
    }
}
