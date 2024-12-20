package ru.tbank.contralwork;

import jakarta.persistence.EntityManager;
import ru.tbank.contralwork.repository.OrderRepository;
import ru.tbank.contralwork.repository.OrderRepositoryImpl;
import ru.tbank.contralwork.service.OrderService;
import ru.tbank.contralwork.service.OrderServiceImpl;
import ru.tbank.contralwork.util.configuration.EntityManagerFactoryConfiguration;
import ru.tbank.contralwork.util.transaction.SimpleTransactionProvider;
import ru.tbank.contralwork.util.transaction.TransactionProvider;

public class Main {

    public static void main(String[] args) {
        try (EntityManager entityManager = EntityManagerFactoryConfiguration.configureEntityManager()) {
            TransactionProvider transactionProvider = new SimpleTransactionProvider(entityManager);
            OrderRepository orderRepository = new OrderRepositoryImpl(entityManager, transactionProvider);
            OrderService orderService = new OrderServiceImpl(orderRepository, transactionProvider);
            orderService.updateOrderStatusById(1L, "created");
        } finally {
            EntityManagerFactoryConfiguration.close();
        }
    }
}
