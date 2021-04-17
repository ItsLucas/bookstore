package me.itslucas.bookstore.controller;

import me.itslucas.bookstore.service.BookService;
import me.itslucas.bookstore.service.CartItemService;
import me.itslucas.bookstore.service.ShoppingCartService;
import me.itslucas.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @PostMapping("/addcart")
    public ModelAndView addcart(@RequestParam(name = "product_id", required = true, defaultValue = "1") Long id,
                                @RequestParam(name = "quantity", required = true, defaultValue = "1") int num,
                                Model model) {
        ModelAndView mav = null;
        mav = new ModelAndView("cart");
        return mav;
    }
}

