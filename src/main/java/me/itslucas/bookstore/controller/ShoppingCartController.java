package me.itslucas.bookstore.controller;

import me.itslucas.bookstore.domain.Book;
import me.itslucas.bookstore.domain.CartItem;
import me.itslucas.bookstore.domain.ShoppingCart;
import me.itslucas.bookstore.domain.User;
import me.itslucas.bookstore.service.BookService;
import me.itslucas.bookstore.service.CartItemService;
import me.itslucas.bookstore.service.ShoppingCartService;
import me.itslucas.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class ShoppingCartController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private BookService bookService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/cart")
    public String shoppingCart(Model model) {
        return "cart";
    }

}

