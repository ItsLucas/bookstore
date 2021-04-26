package me.itslucas.bookstore.conf

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import me.itslucas.bookstore.conf.SecurityConstants.Companion.EXPIRATION_TIME
import me.itslucas.bookstore.conf.SecurityConstants.Companion.KEY
import me.itslucas.bookstore.domain.User
import me.itslucas.bookstore.domain.UserLogin
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.security.Key
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.collections.ArrayList


class AuthenticationFilter :
    UsernamePasswordAuthenticationFilter() {
    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(
        req: HttpServletRequest,
        res: HttpServletResponse
    ): Authentication {
        //TODO fix getAuthenticationManager() is null
        return try {
            val applicationUser: UserLogin =
                ObjectMapper().readValue(req.inputStream, UserLogin::class.java)
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    applicationUser.username,
                    applicationUser.password, ArrayList()
                )
            )
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(
        req: HttpServletRequest?, res: HttpServletResponse, chain: FilterChain?,
        auth: Authentication
    ) {
        val exp = Date(System.currentTimeMillis() + EXPIRATION_TIME)
        val key: Key = Keys.hmacShaKeyFor(KEY.toByteArray())
        val claims: Claims = Jwts.claims().setSubject((auth.getPrincipal() as User).getUsername())
        val token: String =
            Jwts.builder().setClaims(claims).signWith(key, SignatureAlgorithm.HS512).setExpiration(exp).compact()
        res.addHeader("token", token)
    }
}