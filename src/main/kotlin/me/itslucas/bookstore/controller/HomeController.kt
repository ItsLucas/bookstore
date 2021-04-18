package me.itslucas.bookstore.controller

import me.itslucas.bookstore.repository.BookRepository
import me.itslucas.bookstore.service.OrderService
import me.itslucas.bookstore.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.security.Principal

@Controller
class HomeController {

    @Autowired
    private val bookRepository: BookRepository? = null

    @Autowired
    private val userService: UserService? = null

    @Autowired
    private val orderService: OrderService? = null

    @GetMapping("/index")
    fun index(model: Model): String {
        val books = bookRepository!!.findAll(PageRequest.of(0, 4)).toList()
        model.addAttribute("books", books)
        return "index"
    }

    @GetMapping("/product")
    fun product(model: Model): String {
        model.addAttribute("books", bookRepository!!.findAll())
        return "product"
    }

    @GetMapping("/productdetail")
    fun producttest(model: Model, @RequestParam(name = "id") id: Long): String {
        val book = bookRepository!!.findById(id).get()
        model.addAttribute("book", book)
        return "productdetail"
    }

    @GetMapping("/viptest")
    fun viptest(model: Model?, principal: Principal?): String {
        val user = userService?.findByUsername(principal?.name)
        val orders = orderService?.getOrders(user);

        return "vip"
    }
}