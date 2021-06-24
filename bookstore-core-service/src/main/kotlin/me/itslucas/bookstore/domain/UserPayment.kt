package me.itslucas.bookstore.domain

import javax.persistence.*

@Entity
class UserPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    var type: String? = null
    var cardName: String? = null
    var cardNumber: String? = null
    var expiryMonth = 0
    var expiryYear = 0
    var cvc = 0
    var holderName: String? = null
    var isDefaultPayment = false

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User? = null

    @OneToOne(cascade = [CascadeType.ALL], mappedBy = "userPayment")
    var userBilling: UserBilling? = null
}