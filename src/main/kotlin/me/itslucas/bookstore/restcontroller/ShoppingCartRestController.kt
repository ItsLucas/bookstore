package me.itslucas.bookstore.restcontroller

import me.itslucas.bookstore.domain.CartItem
import me.itslucas.bookstore.domain.ShoppingCart
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
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import java.security.Principal


@RestController
class ShoppingCartRestController {
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

    @GetMapping("/api/cart")
    fun shoppingCart(principal: Principal?): List<CartItem>? {
        //val principal = SecurityContextHolder.getContext().authentication.principal as UserDetails;
        var user = userService!!.findByUsername(principal!!.name)
        var shoppingCart = user.shoppingCart;

        var cartItemList = cartItemService?.findByShoppingCart(shoppingCart)?.toList()
        shoppingCartService?.updateShoppingCart(shoppingCart);
        if (cartItemList != null) {
            for (cartItem in cartItemList) {
                cartItem.shoppingCart = null
            }

        }

        return cartItemList
    }

    @PostMapping("/api/addcart")
    fun addcart(
        @RequestParam(name = "product_id", required = true, defaultValue = "1") id: Long?,
        @RequestParam(name = "quantity", required = true, defaultValue = "1") num: Int,
        principal: Principal?
    ): String {
        val user: User = userService!!.findByUsername(principal!!.name)
        val book = id?.let { bookService?.findById(it) };
        val cartItem = cartItemService!!.addBookToCartItem(book?.get(), user, 1)
        return "Success"
    }

    @PostMapping("/api/placeorder")
    fun placeorder(
        principal: Principal?
    ): String {
        val user = userService?.findByUsername(principal?.name)
        val cartItemList = cartItemService!!.findByShoppingCart(user!!.shoppingCart)
        orderService?.createOrder(user.shoppingCart, user)
        shoppingCartService?.clearShoppingCart(user.shoppingCart);
        return "ordersuccess";
    }

    @GetMapping("/api/removeCart")
    fun delcart(
        @RequestParam(name = "product_id", required = true, defaultValue = "1") id: Long?,
        principal: Principal?
    ): String {
        var mav: ModelAndView? = null
        val user: User = userService!!.findByUsername(principal!!.getName())
        val cartItem = cartItemService!!.findByShoppingCart(shoppingCartService?.getByName(user))
        return "deletesuccess"
    }
}