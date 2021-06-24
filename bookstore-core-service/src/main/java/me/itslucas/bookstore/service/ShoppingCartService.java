package me.itslucas.bookstore.service;

import me.itslucas.bookstore.domain.ShoppingCart;
import me.itslucas.bookstore.domain.User;

public interface ShoppingCartService {
    ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);

    void clearShoppingCart(ShoppingCart shoppingCart);

    ShoppingCart getByName(User user);
}
