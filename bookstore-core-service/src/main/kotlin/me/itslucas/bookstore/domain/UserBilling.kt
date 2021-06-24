package me.itslucas.bookstore.domain

import javax.persistence.*

@Entity
class UserBilling {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    var userBillingName: String? = null
    var userBillingStreet1: String? = null
    var userBillingStreet2: String? = null
    var userBillingCity: String? = null
    var userBillingState: String? = null
    var userBillingCountry: String? = null
    var userBillingZipcode: String? = null

    @OneToOne(cascade = [CascadeType.ALL])
    var userPayment: UserPayment? = null
}