package me.itslucas.bookstore.utility

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component
class SpringContextUtil {
    companion object {
        @Autowired
        private var applicationContext: ApplicationContext? = null

        fun getApplicationContext(): ApplicationContext? {
            return applicationContext
        }
    }
}