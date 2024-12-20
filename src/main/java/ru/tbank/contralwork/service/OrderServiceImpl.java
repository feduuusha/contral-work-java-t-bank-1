package ru.tbank.contralwork.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import ru.tbank.contralwork.entity.Order;
import ru.tbank.contralwork.entity.Product;
import ru.tbank.contralwork.repository.OrderRepository;
import ru.tbank.contralwork.util.transaction.TransactionProvider;

import java.util.List;

@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final String TARGET_STATUS = "paid for";

    private final OrderRepository orderRepository;
    private final TransactionProvider transactionProvider;

    @Override
    public void updateOrderStatusById(Long id, String status) {
        transactionProvider.doInTransaction(() -> {
            Order order = orderRepository
                    .findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Order with ID: " + id + " not found"));
            if (TARGET_STATUS.equals(status)) {
                order.getProducts()
                        .forEach((product) -> product.setQuantityInStock(product.getQuantityInStock() - 1));
            }
            order.setStatus(status);
            return null;
        });
    }

    @Override
    public Order createOrder(String country, String region, String locality, String street, String houseNumber, String receiverName, String status, List<Product> products) {
        return orderRepository.save(new Order(null, country, region, locality, street, houseNumber, receiverName, status, products));
    }
}
