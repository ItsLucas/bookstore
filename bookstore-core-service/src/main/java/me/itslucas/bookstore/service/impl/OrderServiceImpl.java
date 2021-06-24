package me.itslucas.bookstore.service.impl;

import me.itslucas.bookstore.domain.*;
import me.itslucas.bookstore.repository.OrderRepository;
import me.itslucas.bookstore.service.CartItemService;
import me.itslucas.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemService cartItemService;

    public synchronized Order createOrder(ShoppingCart shoppingCart,
                                          User user) {
        Order order = new Order();
        order.setOrderStatus("created");

        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        for (CartItem cartItem : cartItemList) {
            Book book = cartItem.getBook();
            cartItem.setOrder(order);
            book.setInStockNumber(book.getInStockNumber() - cartItem.getQty());
        }

        order.setCartItemList(null);
        order.setOrderDate(Calendar.getInstance().getTime());
        order.setOrderTotal(shoppingCart.getGrandTotal());
        order.setUser(user);
        order = orderRepository.save(order);

        return order;
    }

    public List<Order> getOrders(User user) {
        return orderRepository.findOrdersByUser(user);
    }

    public Order findOne(Long id) {
        return orderRepository.findById(id).get();
    }
}
