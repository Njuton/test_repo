package com.example.myapp.config;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author rmartynov
 * @since 04.05.2025
 */
@Component
public class DataSourceRegistryInfo {
    private AtomicBoolean readOnlyDSEnabled = new AtomicBoolean(false);
    private AtomicBoolean shardingDSEnabled = new AtomicBoolean(false);

    public AtomicBoolean getReadOnlyDSEnabled() {
        return readOnlyDSEnabled;
    }

    public void setReadOnlyDSEnabled(AtomicBoolean readOnlyDSEnabled) {
        this.readOnlyDSEnabled = readOnlyDSEnabled;
    }

    public AtomicBoolean getShardingDSEnabled() {
        return shardingDSEnabled;
    }

    public void setShardingDSEnabled(AtomicBoolean shardingDSEnabled) {
        this.shardingDSEnabled = shardingDSEnabled;
    }
}