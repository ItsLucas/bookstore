package me.itslucas.bookstore.restcontroller

import me.itslucas.bookstore.domain.Book
import me.itslucas.bookstore.domain.CartItem
import me.itslucas.bookstore.domain.User
import me.itslucas.bookstore.repository.BookRepository
import me.itslucas.bookstore.repository.CartItemRepository
import me.itslucas.bookstore.service.CartItemService
import me.itslucas.bookstore.service.OrderService
import me.itslucas.bookstore.service.ShoppingCartService
import me.itslucas.bookstore.service.UserService
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import java.security.Principal
import kotlin.properties.Delegates


@RestController
@Component
class ShoppingCartRestController {
    @Autowired
    private val kafkaTemplate: KafkaTemplate<String,String>? = null

    @Autowired
    private val cartItemService: CartItemService? = null

    @Autowired
    private val cartItemRepository: CartItemRepository? = null

    @Autowired
    private val shoppingCartService: ShoppingCartService? = null

    @Autowired
    private val userService: UserService? = null

    @Autowired
    private val bookService: BookRepository? = null

    @Autowired
    private val orderService: OrderService? = null

    val LOG = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/api/cart")
    fun shoppingCart(principal: Principal?): List<CartItem>? {
        val user = userService!!.findByUsername(principal!!.name)
        val shoppingCart = user.shoppingCart
        val cartItemList = cartItemService?.findByShoppingCart(shoppingCart)?.toList()
        if (cartItemList != null) {
            for (cartItem in cartItemList) {
                cartItem.shoppingCart = null
            }

        }

        return cartItemList
    }

    class AddCartAPI {
        var quantity by Delegates.notNull<Int>()
        var product_id by Delegates.notNull<Long>()
    }

    @PostMapping("/api/addcart")
    @ResponseBody
    fun addcart(
        @RequestBody req: AddCartAPI,
        principal: Principal?
    ): String {
        val user: User = userService!!.findByUsername(principal!!.name)
        val book: Book = bookService!!.findById(req.product_id).get()
        cartItemService!!.addBookToCartItem(book, user, req.quantity)
        LOG.warn("User ${user.username} Book ${req.product_id}:${book.title} x ${req.quantity}")
        return "Success"
    }

    @PostMapping("/api/placeorder")
    fun placeorder(
        principal: Principal?
    ): String {
        kafkaTemplate?.send("billing",principal?.name)
        return "ordersuccess"
    }

    @KafkaListener(topics = ["billing"])
    fun real_placeorder(record: ConsumerRecord<String, String>) {
        val user = userService?.findByUsername(record.value())
        val cartItemList = cartItemService!!.findByShoppingCart(user!!.shoppingCart)
        orderService?.createOrder(user.shoppingCart, user)
        shoppingCartService?.clearShoppingCart(user.shoppingCart)
    }

    class DeleteCartAPI {
        var product_id by Delegates.notNull<Long>()
    }

    @PostMapping("/api/removeCart")
    fun delcart(
        @RequestBody req: DeleteCartAPI,
        principal: Principal?
    ): String {
        val user: User = userService!!.findByUsername(principal!!.name)
        val book = bookService!!.findById(req.product_id).get()
        val cartItem = cartItemRepository!!.findByShoppingCartAndBook(user.shoppingCart,book)[0]
        cartItemService!!.removeCartItem(cartItem)
        return "deletesuccess"
    }
}