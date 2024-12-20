package ru.tbank.contralwork.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import ru.tbank.contralwork.entity.Order;
import ru.tbank.contralwork.util.transaction.TransactionProvider;

import java.util.Optional;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final EntityManager entityManager;
    private final TransactionProvider transactionProvider;

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Order.class, id));
    }

    @Override
    public Order save(Order order) {
        return (Order) transactionProvider.doInTransaction(() -> {
            entityManager.persist(order);
            return order;
        });
    }
}
