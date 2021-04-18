package me.itslucas.bookstore.controller

import me.itslucas.bookstore.domain.User
import me.itslucas.bookstore.domain.security.UserRole
import me.itslucas.bookstore.repository.RoleRepository
import me.itslucas.bookstore.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import java.util.*

@Controller
class UserController {

    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    @Autowired
    private val userService: UserService? = null

    @Autowired
    private val roleRepository: RoleRepository? = null


    @GetMapping("/login")
    fun login(model: Model?): String {
        return "login"
    }

    @GetMapping("/register")
    fun register(model: Model?): String {
        return "register"
    }

    @GetMapping("logout")
    fun logout(model: Model?): String {
        return "login"
    }

    @PostMapping("/registercheck")
    @Throws(Exception::class)
    fun register(
        @RequestParam(name = "username", required = false, defaultValue = "World") name: String,
        @RequestParam(name = "password", required = false, defaultValue = "World") pass: String,
        @RequestParam(name = "email", required = false, defaultValue = "World") email: String,
        @RequestParam(name = "phone", required = false, defaultValue = "World") phone: String,
        model: Model?
    ): ModelAndView {
        var modelAndView: ModelAndView? = null
        if (userService!!.findByUsername(name) == null) {
            val user = User()
            user.setUsername(name)
            user.setPassword(pass)
            user.email = email
            user.phone = phone

            val role = roleRepository!!.findByName("ROLE_USER")
            val userRoles: MutableSet<UserRole> = HashSet()
            userRoles.add(UserRole(user as User?, role))
            userService.createUser(user, userRoles)
            val token = UUID.randomUUID().toString()
            userService.createPasswordResetTokenForUser(user, token)
            modelAndView = ModelAndView("login")
        } else modelAndView = ModelAndView("error")
        return modelAndView
    }
}