package me.itslucas.bookstore.restcontroller

import me.itslucas.bookstore.domain.User
import me.itslucas.bookstore.repository.RoleRepository
import me.itslucas.bookstore.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/users")
class UserController() {
    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    @Autowired
    private val userService: UserService? = null

    @Autowired
    private val roleRepository: RoleRepository? = null

    @PostMapping("/record")
    fun signUp(
        @RequestParam(name = "username", required = false, defaultValue = "World") name: String,
        @RequestParam(name = "password", required = false, defaultValue = "World") pass: String,
        @RequestParam(name = "email", required = false, defaultValue = "World") email: String,
        @RequestParam(name = "phone", required = false, defaultValue = "World") phone: String
    ) {
        if (userService!!.findByUsername(name) == null) {
            val user = User()
            user.setUsername(name)
            user.setPassword(pass)
            user.email = email
            user.phone = phone

            //val role = roleRepository!!.findByName("ROLE_USER")
            //val userRoles: MutableSet<UserRole> = HashSet()
            //userRoles.add(UserRole(user as User?, role))
            userService.createUser(user)
            val token = UUID.randomUUID().toString()
            userService.createPasswordResetTokenForUser(user, token)
        }
    }

}