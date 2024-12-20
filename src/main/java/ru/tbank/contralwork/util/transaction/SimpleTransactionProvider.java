package ru.tbank.contralwork.util.transaction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class SimpleTransactionProvider implements TransactionProvider {

    private final EntityManager entityManager;

    @Override
    public Object doInTransaction(Supplier<Object> function) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            var object = function.get();
            transaction.commit();
            return object;
        } catch (Throwable throwable) {
            transaction.rollback();
            throw  throwable;
        }
    }
}
