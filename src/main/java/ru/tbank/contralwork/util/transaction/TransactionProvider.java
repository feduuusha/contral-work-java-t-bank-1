package ru.tbank.contralwork.util.transaction;

import java.util.function.Supplier;

public interface TransactionProvider {
    Object doInTransaction(Supplier<Object> function);
}
