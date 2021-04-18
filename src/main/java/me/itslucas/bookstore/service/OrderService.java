package me.itslucas.bookstore.service;

import me.itslucas.bookstore.domain.*;

public interface OrderService {
    Order createOrder(ShoppingCart shoppingCart,
                      User user);

    Order findOne(Long id);
}
