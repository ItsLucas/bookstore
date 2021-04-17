package me.itslucas.bookstore.controller

import me.itslucas.bookstore.domain.Login
import me.itslucas.bookstore.domain.User
import me.itslucas.bookstore.domain.security.Role
import me.itslucas.bookstore.domain.security.UserRole
import me.itslucas.bookstore.service.UserService
import me.itslucas.bookstore.service.impl.UserSecurityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class UserController {
    @Autowired
    private val userService: UserService? = null

    @Autowired
    private val userSecurityService: UserSecurityService? = null

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

    @RequestMapping(value = ["/authenticate"], method = [RequestMethod.POST])
    fun authenticate(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        @ModelAttribute("login") login: Login,
        model: Model?
    ): ModelAndView? {
        var mav: ModelAndView? = null
//        Logger.getLogger("test").info("111")
//        val user = userService!!.findByUsername(login.username)
//        if (user == null) {
//            mav = ModelAndView("error")
//        } else if (user.password == login.password) {
//            mav = ModelAndView("index")
//            mav.addObject("name", user.username)
//        }
        return mav
    }

    @PostMapping("/registercheck")
    @Throws(Exception::class)
    fun reg(
        @RequestParam(name = "username", required = false, defaultValue = "World") name: String?,
        @RequestParam(name = "password", required = false, defaultValue = "World") pass: String?,
        @RequestParam(name = "email", required = false, defaultValue = "World") email: String?,
        @RequestParam(name = "phone", required = false, defaultValue = "World") phone: String?,
        model: Model?
    ): ModelAndView {
        var mav: ModelAndView? = null
        val ud = userSecurityService!!.loadUserByUsername(name)
        if (null == ud) {
            val user = User()
            user.setUsername(name)
            user.setPassword(pass)
            user.email = email
            user.phone = phone
            val role = Role()
            role.roleId = 1
            role.name = "ROLE_USER"
            val userRoles: MutableSet<UserRole> = HashSet()
            userRoles.add(UserRole(user as User?, role))
            userService?.createUser(user, userRoles)
            val token = UUID.randomUUID().toString()
            userService?.createPasswordResetTokenForUser(user, token)
            mav = ModelAndView("login")
        } else mav = ModelAndView("error")
        return mav
    }
}