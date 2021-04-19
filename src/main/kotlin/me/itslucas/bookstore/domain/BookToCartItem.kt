package me.itslucas.bookstore.domain

import javax.persistence.*

@Entity
class BookToCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "book_id")
    var book: Book? = null

    @ManyToOne
    @JoinColumn(name = "cart_item_id")
    var cartItem: CartItem? = null
}