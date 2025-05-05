package com.example.myapp.config;

/**
 * @author rmartynov
 * @since 27.03.2025
 */
public class TransactionContextHolder {

    private static final ThreadLocal<TransactionType> contextHolder = new ThreadLocal<>();

    public static void setTransactionType(TransactionType transactionType) {
        contextHolder.set(transactionType);
    }

    public static TransactionType getTransactionType() {
        return contextHolder.get();
    }

    public static void clear() {
        contextHolder.remove();
    }

    public enum TransactionType {
        READ_ONLY, READ_WRITE, SHARDING
    }
}
