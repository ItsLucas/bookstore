package me.itslucas.bookstore.domain.security

import org.springframework.security.core.GrantedAuthority

class Authority(private val authority: String) : GrantedAuthority {
    override fun getAuthority(): String {
        return authority
    }
}