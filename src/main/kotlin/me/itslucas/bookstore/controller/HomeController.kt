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

    @GetMapping("/")
    fun index2(model: Model, principal: Principal?): String {
        val books = bookRepository!!.findAll(PageRequest.of(0, 4)).toList()
        model.addAttribute("books", books)
        val user = userService?.findByUsername(principal?.name)
        model.addAttribute("user", user)
        return "index"
    }

    @GetMapping("/index")
    fun index(model: Model, principal: Principal?): String {
        val books = bookRepository!!.findAll(PageRequest.of(0, 4)).toList()
        model.addAttribute("books", books)
        val user = userService?.findByUsername(principal?.name)
        model.addAttribute("user", user)
        return "index"
    }

    @GetMapping("/product")
    fun product(model: Model, principal: Principal?): String {
        model.addAttribute("books", bookRepository!!.findAll())
        val user = userService?.findByUsername(principal?.name)
        model.addAttribute("user", user)
        return "product"
    }

    @GetMapping("/productdetail")
    fun producttest(model: Model, principal: Principal?, @RequestParam(name = "id") id: Long): String {
        val book = bookRepository!!.findById(id).get()
        model.addAttribute("book", book)
        val user = userService?.findByUsername(principal?.name)
        model.addAttribute("user", user)
        return "productdetail"
    }

    @GetMapping("/vip")
    fun viptest(model: Model?, principal: Principal?): String {
        val user = userService?.findByUsername(principal?.name)
        model?.addAttribute("user", user)
        val orders = orderService?.getOrders(user)

        model?.addAttribute("orders", orders)
        return "vip"
    }
}