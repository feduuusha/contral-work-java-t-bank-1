package ru.tbank.contralwork.service;

import ru.tbank.contralwork.entity.Order;
import ru.tbank.contralwork.entity.Product;

import java.util.List;

public interface OrderService {

    void updateOrderStatusById(Long id, String status);
    Order createOrder(String country, String region, String locality, String street, String houseNumber,
                      String receiverName, String status, List<Product> products);
}
