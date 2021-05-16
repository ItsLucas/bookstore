package me.itslucas.bookstore.repository;

import me.itslucas.bookstore.domain.Order;
import me.itslucas.bookstore.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findOrdersByUser(User user);
}
