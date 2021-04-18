package me.itslucas.bookstore.domain

import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user_order")
class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    var orderDate: Date? = null
    var orderStatus: String? = null
    var orderTotal: BigDecimal? = null

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    var cartItemList: List<CartItem>? = null

    @ManyToOne
    var user: User? = null
}