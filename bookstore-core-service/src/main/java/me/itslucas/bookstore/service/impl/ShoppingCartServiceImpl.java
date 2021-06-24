package me.itslucas.bookstore.service.impl;

import me.itslucas.bookstore.domain.CartItem;
import me.itslucas.bookstore.domain.ShoppingCart;
import me.itslucas.bookstore.domain.User;
import me.itslucas.bookstore.repository.ShoppingCartRepository;
import me.itslucas.bookstore.service.CartItemService;
import me.itslucas.bookstore.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {
        BigDecimal cartTotal = new BigDecimal(0);

        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        for (CartItem cartItem : cartItemList) {
            if (cartItem.getBook().getInStockNumber() > 0) {
                cartItemService.updateCartItem(cartItem);
                cartTotal = cartTotal.add(cartItem.getSubtotal());
            }
        }

        shoppingCart.setGrandTotal(cartTotal);

        shoppingCartRepository.save(shoppingCart);

        return shoppingCart;
    }

    public void clearShoppingCart(ShoppingCart shoppingCart) {
        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        for (CartItem cartItem : cartItemList) {
            cartItem.setShoppingCart(null);
            cartItemService.save(cartItem);

            shoppingCart.setGrandTotal(new BigDecimal(0));

            shoppingCartRepository.save(shoppingCart);
        }
    }

    @Override
    public ShoppingCart getByName(User user) {
        return shoppingCartRepository.findShoppingCartByUser(user);
    }
}
