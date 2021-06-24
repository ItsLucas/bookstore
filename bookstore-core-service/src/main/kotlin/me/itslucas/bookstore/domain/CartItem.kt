package me.itslucas.bookstore.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import java.math.BigDecimal
import javax.persistence.*

@Entity
class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    var qty = 0
    var subtotal: BigDecimal? = null

    @OneToOne
    var book: Book? = null

    @OneToMany(mappedBy = "cartItem")
    @JsonIgnore
    var bookToCartItemList: List<BookToCartItem>? = null

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    var shoppingCart: ShoppingCart? = null

    @ManyToOne
    @JoinColumn(name = "order_id")
    var order: Order? = null
}