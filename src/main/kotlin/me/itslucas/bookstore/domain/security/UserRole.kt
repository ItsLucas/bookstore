package me.itslucas.bookstore.domain.security

import me.itslucas.bookstore.domain.User
import javax.persistence.*

@Entity
@Table(name = "user_role")
class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var userRoleId: Long? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    var user: User? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    var role: Role? = null

    constructor()
    constructor(user: User?, role: Role?) {
        this.user = user
        this.role = role
    }
}