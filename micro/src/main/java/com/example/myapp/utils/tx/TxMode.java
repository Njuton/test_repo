package com.example.myapp.utils.tx;

import org.springframework.transaction.TransactionDefinition;

/**
 * @author rmartynov
 * @since 15.01.2025
 */
public enum TxMode {

    /**
     * Создаёт новую транзакцию. Если уже существует текущая транзакция, она приостанавливается.
     */
    NEW(TransactionDefinition.PROPAGATION_REQUIRES_NEW, TransactionDefinition.ISOLATION_READ_COMMITTED, false),

    /**
     * Выполняет код в режиме "только чтение" в рамках текущей транзакции.
     */
    READ_ONLY(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, true),

    /**
     * Создаёт новую транзакцию в режиме "только чтение".
     */
    NEW_READ_ONLY(TransactionDefinition.PROPAGATION_REQUIRES_NEW, TransactionDefinition.ISOLATION_READ_COMMITTED, true),

    /**
     * Использует текущую транзакцию, если она существует, либо создаёт новую.
     */
    CURRENT_OR_NEW(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);

    private final int propagationBehavior;
    private final int isolationLevel;
    private final boolean readOnly;

    TxMode(int propagationBehavior, int isolationLevel, boolean readOnly) {
        this.propagationBehavior = propagationBehavior;
        this.isolationLevel = isolationLevel;
        this.readOnly = readOnly;
    }

    public int getPropagationBehavior() {
        return propagationBehavior;
    }

    public int getIsolationLevel() {
        return isolationLevel;
    }

    public boolean isReadOnly() {
        return readOnly;
    }
}