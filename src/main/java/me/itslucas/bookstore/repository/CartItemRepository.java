package me.itslucas.bookstore.repository;

import me.itslucas.bookstore.domain.CartItem;
import me.itslucas.bookstore.domain.Order;
import me.itslucas.bookstore.domain.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

    List<CartItem> findByOrder(Order order);
}
