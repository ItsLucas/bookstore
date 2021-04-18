package me.itslucas.bookstore.controller

import me.itslucas.bookstore.domain.User
import me.itslucas.bookstore.repository.BookRepository
import me.itslucas.bookstore.service.CartItemService
import me.itslucas.bookstore.service.OrderService
import me.itslucas.bookstore.service.ShoppingCartService
import me.itslucas.bookstore.service.UserService
import me.itslucas.bookstore.utility.USConstants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import java.security.Principal


@Controller
class ShoppingCartController {
    @Autowired
    private val cartItemService: CartItemService? = null

    @Autowired
    private val shoppingCartService: ShoppingCartService? = null

    @Autowired
    private val userService: UserService? = null

    @Autowired
    private val bookService: BookRepository? = null

    @Autowired
    private val orderService: OrderService? = null

    @GetMapping("/cart")
    fun shoppingCart(model: Model?, principal: Principal?): String {
        //val principal = SecurityContextHolder.getContext().authentication.principal as UserDetails;
        var user = userService!!.findByUsername(principal!!.name)
        var shoppingCart = user.shoppingCart;

        var cartItemList = cartItemService?.findByShoppingCart(shoppingCart);

        shoppingCartService?.updateShoppingCart(shoppingCart);

        model?.addAttribute("cartItems", cartItemList);
        model?.addAttribute("shoppingCart", shoppingCart)
        return "cart"
    }

    @PostMapping("/addcart")
    fun addcart(
            @RequestParam(name = "product_id", required = true, defaultValue = "1") id: Long?,
            @RequestParam(name = "quantity", required = true, defaultValue = "1") num: Int,
            model: Model?,
            principal: Principal?
    ): ModelAndView {
        var mav: ModelAndView? = null

        val user: User = userService!!.findByUsername(principal!!.getName())
        val book = id?.let { bookService?.findById(it) };
        mav = ModelAndView("product");
        val cartItem = cartItemService!!.addBookToCartItem(book?.get(), user, 1)
        return mav
    }

    @PostMapping("/placeorder")
    fun placeorder(
            model: Model?,
            principal: Principal?
    ): String {
        val user = userService?.findByUsername(principal?.name)
        val cartItemList = cartItemService!!.findByShoppingCart(user!!.shoppingCart)
        orderService?.createOrder(user.shoppingCart, user)
        shoppingCartService?.clearShoppingCart(user.shoppingCart);
        return "ordersuccess";
    }

    @GetMapping("/removeCart")
    fun delcart(
            @RequestParam(name = "product_id", required = true, defaultValue = "1") id: Long?,
            model: Model?,
            principal: Principal?
    ): ModelAndView {
        var mav: ModelAndView? = null
        val user: User = userService!!.findByUsername(principal!!.getName())

        mav = ModelAndView("product");
        val cartItem = cartItemService!!.findByShoppingCart(shoppingCartService?.getByName(user))

        return mav
    }
}