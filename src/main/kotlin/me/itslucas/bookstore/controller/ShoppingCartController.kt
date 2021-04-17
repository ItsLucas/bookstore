package me.itslucas.bookstore.controller

import me.itslucas.bookstore.service.CartItemService
import me.itslucas.bookstore.service.ShoppingCartService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
class ShoppingCartController {
    @Autowired
    private val cartItemService: CartItemService? = null

    @Autowired
    private val shoppingCartService: ShoppingCartService? = null

    @GetMapping("/cart")
    fun shoppingCart(model: Model?): String {
        return "cart"
    }

    @PostMapping("/addcart")
    fun addcart(
        @RequestParam(name = "product_id", required = true, defaultValue = "1") id: Long?,
        @RequestParam(name = "quantity", required = true, defaultValue = "1") num: Int,
        model: Model?
    ): ModelAndView {
        var mav: ModelAndView? = null
        mav = ModelAndView("cart")
        return mav
    }
}