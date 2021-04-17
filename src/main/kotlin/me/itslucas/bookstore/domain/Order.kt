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
    var shippingDate: Date? = null
    var shippingMethod: String? = null
    var orderStatus: String? = null
    var orderTotal: BigDecimal? = null

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    var cartItemList: List<CartItem>? = null

    @OneToOne(cascade = [CascadeType.ALL])
    var shippingAddress: ShippingAddress? = null

    @OneToOne(cascade = [CascadeType.ALL])
    var billingAddress: BillingAddress? = null

    @OneToOne(cascade = [CascadeType.ALL])
    var payment: Payment? = null

    @ManyToOne
    var user: UserOld? = null
}