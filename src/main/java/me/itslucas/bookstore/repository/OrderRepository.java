package me.itslucas.bookstore.repository;

import me.itslucas.bookstore.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
