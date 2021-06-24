package me.itslucas.bookstore.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.web.multipart.MultipartFile
import javax.persistence.*

@Entity
class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    var title: String? = null
    var author: String? = null
    var publisher: String? = null
    var publicationDate: String? = null
    var language: String? = null
    var category: String? = null
    var numberOfPages = 0
    var format: String? = null
    var isbn = 0
    var shippingWeight = 0.0
    var listPrice = 0.0
    var active = true

    @Column(columnDefinition = "text")
    var description: String? = null
    var inStockNumber = 0

    @Transient
    var bookImage: MultipartFile? = null

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    var bookToCartItemList: List<BookToCartItem>? = null
}