package me.itslucas.bookstore.restcontroller

import me.itslucas.bookstore.domain.Book
import me.itslucas.bookstore.domain.Order
import me.itslucas.bookstore.domain.User
import me.itslucas.bookstore.repository.BookRepository
import me.itslucas.bookstore.service.OrderService
import me.itslucas.bookstore.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class HomeRestController {
    private val LOG = LoggerFactory.getLogger(HomeRestController::class.java)

    @Autowired
    private val bookRepository: BookRepository? = null

    @Autowired
    private val userService: UserService? = null

    @Autowired
    private val orderService: OrderService? = null

    @GetMapping("/api/index")
    fun index(principal: Principal?): List<Book> {
        val books = bookRepository!!.findAll(PageRequest.of(0, 4)).toList()
        return books
    }

    @GetMapping("/api/allproducts")
    fun products(principal: Principal?): List<Book> {
        val books = bookRepository!!.findAll().toList()
        return books
    }

    @GetMapping("/api/productdetail")
    fun producttest(principal: Principal?, @RequestParam(name = "id") id: Long): Book {
        val book = bookRepository!!.findById(id).get()
        return book
    }

    @GetMapping("/api/userinfo")
    fun viptest(authentication: Authentication?): Pair<User?, List<Order>?> {
        val user = userService?.findByUsername(authentication?.name)
        user?.shoppingCart = null
        LOG.info(authentication?.name)
        val orders = orderService?.getOrders(user)
        if (orders != null) {
            for (order in orders) {
                order.cartItemList = null
                order.user = null
            }
        }
        return Pair(user, orders?.toList())
    }
}