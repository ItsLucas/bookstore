package me.itslucas.bookstore.conf

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import me.itslucas.bookstore.conf.SecurityConstants.Companion.KEY
import me.itslucas.bookstore.service.UserService
import org.slf4j.LoggerFactory
import me.itslucas.bookstore.conf.SecurityConstants.Companion.HEADER_NAME
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class AuthorizationFilter(authManager: AuthenticationManager?) : BasicAuthenticationFilter(authManager) {
    private val LOG = LoggerFactory.getLogger(AuthorizationFilter::class.java)
    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        val header = request.getHeader(HEADER_NAME)
        LOG.info("authorization: $header")
        if (header == null) {
            chain.doFilter(request, response)
            return
        }
        val authentication = authenticate(request)
        SecurityContextHolder.getContext().authentication = authentication
        chain.doFilter(request, response)
    }

    private fun authenticate(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        val token = request.getHeader(HEADER_NAME)
        if (token != null) {
            val user = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(KEY.toByteArray()))
                .build()
                .parseClaimsJws(token)
                .body
            return if (user != null) {
                UsernamePasswordAuthenticationToken(user, null, ArrayList())
            } else {
                null
            }
        }
        return null
    }
}