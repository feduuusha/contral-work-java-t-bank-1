package ru.tbank.contralwork.repository;

import ru.tbank.contralwork.entity.Order;

import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(Long id);
    Order save(Order order);
}
