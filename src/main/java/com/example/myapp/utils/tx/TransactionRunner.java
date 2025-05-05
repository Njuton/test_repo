package com.example.myapp.utils.tx;

import com.example.myapp.config.TransactionContextHolder;
import com.example.myapp.config.TransactionContextHolder.TransactionType;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.function.Supplier;

/**
 * @author rmartynov
 * @since 15.01.2025
 */
@Component
public class TransactionRunner {

    private final TransactionTemplate transactionTemplate;

    public TransactionRunner(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    /**
     * Выполняет переданный код в рамках транзакции с заданным режимом.
     *
     * @param action          Код, который нужно выполнить в транзакции.
     * @param transactionType тип транзакции, влияющий на выбор ноды для выполнения запроса (null - дефолтная нода)
     * @param mode            Режим транзакции (TransactionMode).
     */
    public void runInTransaction(Runnable action, TxMode mode, @Nullable TransactionType transactionType) {
        configureTransactionTemplate(mode);
        try {
            if (transactionType == null) {
                TransactionContextHolder.setTransactionType(determineTransactionType(mode));
            } else {
                TransactionContextHolder.setTransactionType(transactionType);
            }
            TransactionContextHolder.setTransactionType(determineTransactionType(mode));
            transactionTemplate.executeWithoutResult(status -> action.run());
        } finally {
            TransactionContextHolder.clear();
        }
    }

    /**
     * Выполняет переданный код в рамках транзакции с заданным режимом и возвращает результат.
     *
     * @param action          Код, который нужно выполнить в транзакции.
     * @param mode            Режим транзакции (TransactionMode).
     * @param transactionType тип транзакции, влияющий на выбор ноды для выполнения запроса (null - дефолтная нода)
     * @param <T>             Тип возвращаемого значения.
     * @return Результат выполнения кода.
     */
    public <T> T runInTransaction(Supplier<T> action, TxMode mode, @Nullable TransactionType transactionType) {
        configureTransactionTemplate(mode);
        try {
            if (transactionType == null) {
                TransactionContextHolder.setTransactionType(determineTransactionType(mode));
            } else {
                TransactionContextHolder.setTransactionType(transactionType);
            }
            return transactionTemplate.execute(status -> action.get());
        } finally {
            TransactionContextHolder.clear();
        }
    }

    private static TransactionType determineTransactionType(TxMode mode) {
        return mode.isReadOnly() ? TransactionType.READ_ONLY : TransactionType.READ_WRITE;
    }

    private void configureTransactionTemplate(TxMode mode) {
        transactionTemplate.setPropagationBehavior(mode.getPropagationBehavior());
        transactionTemplate.setIsolationLevel(mode.getIsolationLevel());
        transactionTemplate.setReadOnly(mode.isReadOnly());
    }
}
