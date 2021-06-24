package me.itslucas.bookstore.conf

class SecurityConstants {
    companion object {
        const val SIGN_UP_URL = "/users/record"
        const val KEY = "q3t6w9z\$C&F)J@NcQfTjWnZr4u7x!A%D*G-KaPdSgUkXp2s5v8y/B?E(H+MbQeTh"
        const val HEADER_NAME = "authorization"
        const val EXPIRATION_TIME = 1000L * 60 * 30
    }
}