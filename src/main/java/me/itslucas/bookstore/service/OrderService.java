package me.itslucas.bookstore.service;

import me.itslucas.bookstore.domain.*;

public interface OrderService {
    Order createOrder(ShoppingCart shoppingCart,
                      ShippingAddress shippingAddress,
                      BillingAddress billingAddress,
                      Payment payment,
                      String shippingMehod,
                      UserOld user);

    Order findOne(Long id);
}
