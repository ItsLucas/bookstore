package me.itslucas.bookstore.repository

import me.itslucas.bookstore.domain.security.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(name: String): Role
}