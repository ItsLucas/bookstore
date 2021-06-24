package me.itslucas.bookstore.service;

import me.itslucas.bookstore.domain.Order;
import me.itslucas.bookstore.domain.ShoppingCart;
import me.itslucas.bookstore.domain.User;

import java.util.List;

public interface OrderService {
    Order createOrder(ShoppingCart shoppingCart,
                      User user);

    List<Order> getOrders(User user);

    Order findOne(Long id);
}
