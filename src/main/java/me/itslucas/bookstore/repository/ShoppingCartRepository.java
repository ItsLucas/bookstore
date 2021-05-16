package me.itslucas.bookstore.repository;

import me.itslucas.bookstore.domain.ShoppingCart;
import me.itslucas.bookstore.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {
    ShoppingCart findShoppingCartByUser(User user);
}
