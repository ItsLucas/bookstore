package me.itslucas.bookstore.conf

import me.itslucas.bookstore.utility.SecurityUtility.passwordEncoder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler


@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    private val userDetailsService: UserDetailsService? = null

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http {
            authorizeRequests {
                authorize("/cart", hasRole("USER"))
                authorize("/", permitAll)
                authorize("/h2-console/**", permitAll)
                authorize(anyRequest, permitAll)
            }
            formLogin {
                loginPage = "/login"
                permitAll
                authenticationFailureHandler = SimpleUrlAuthenticationFailureHandler()
            }
            logout { permitAll }
            httpBasic { }
            csrf { disable() }
            headers { frameOptions { disable() } }
        }
    }

    @Throws(Exception::class)
    @Override
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(authProvider())
    }

    @Bean
    fun authProvider(): DaoAuthenticationProvider? {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsService)
        authProvider.setPasswordEncoder(passwordEncoder())
        return authProvider
    }

    @Bean
    @Override
    public override fun userDetailsService(): UserDetailsService? {
        return userDetailsService
    }
}