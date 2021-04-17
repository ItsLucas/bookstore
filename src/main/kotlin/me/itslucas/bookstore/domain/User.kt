package me.itslucas.bookstore.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import me.itslucas.bookstore.domain.security.Authority
import me.itslucas.bookstore.domain.security.UserRole
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.HashSet
import java.util.function.Consumer
import javax.persistence.*

@Entity
class User : UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    var id: Long? = null
    var firstName: String? = null
    var lastName: String? = null

    private var username:String? =null
    private var password:String? =null

    @Column(name = "email", nullable = false, updatable = false)
    var email: String? = null
    var phone: String? = null
    private var enabled = true

    @OneToOne(cascade = [CascadeType.ALL], mappedBy = "user")
    var shoppingCart: ShoppingCart? = null

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "user")
    var userShippingList: List<UserShipping>? = null

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "user")
    var userPaymentList: List<UserPayment>? = null

    @OneToMany(mappedBy = "user")
    var orderList: List<Order>? = null

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JsonIgnore
    var userRoles: Set<UserRole> = HashSet()

    override fun getAuthorities(): Collection<GrantedAuthority> {
        val authorites: MutableSet<GrantedAuthority> = HashSet()
        userRoles.forEach(Consumer { ur: UserRole ->
            ur.role?.name?.let {
                Authority(
                    it
                )
            }?.let {
                authorites.add(
                    it
                )
            }
        })
        return authorites
    }

    override fun getUsername() = username
    override fun getPassword() = password
    fun setUsername(name: String?) = username
    fun setPassword(pass: String?) = password

    override fun isAccountNonExpired(): Boolean {
        // TODO Auto-generated method stub
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        // TODO Auto-generated method stub
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        // TODO Auto-generated method stub
        return true
    }

    override fun isEnabled(): Boolean {
        return enabled
    }

    fun setEnabled(enabled: Boolean) {
        this.enabled = enabled
    }
}