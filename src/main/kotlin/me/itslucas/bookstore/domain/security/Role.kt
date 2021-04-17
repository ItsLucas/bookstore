package me.itslucas.bookstore.domain.security

import java.util.HashSet
import javax.persistence.*

@Entity
class Role {
    @Id
    var roleId = 0
    var name: String? = null

    @OneToMany(mappedBy = "role", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var userRoles: Set<UserRole> = HashSet()
}