package me.itslucas.bookstore.conf

import me.itslucas.bookstore.service.impl.UserSecurityService
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler


@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http {
            authorizeRequests {
                authorize("/cart", hasRole("USER"))
                authorize("/", permitAll)
                authorize("/h2-console/**",permitAll)
                authorize(anyRequest, permitAll)
            }
            formLogin {
                loginPage = "/login"
                permitAll
                authenticationFailureHandler = SimpleUrlAuthenticationFailureHandler()
            }
            logout { permitAll }
            httpBasic {  }
            csrf { disable() }
            headers { frameOptions { disable() } }
        }
    }

    @Throws(Exception::class)
    @Override
    override fun configure(auth: AuthenticationManagerBuilder){
        auth.authenticationProvider()
    }

    @Bean
    @Override
    public override fun userDetailsService(): UserDetailsService {
        return UserSecurityService();
    }
}