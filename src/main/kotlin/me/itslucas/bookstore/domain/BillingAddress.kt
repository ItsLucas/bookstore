package me.itslucas.bookstore.domain

import javax.persistence.*

@Entity
class BillingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    var billingAddressName: String? = null
    var billingAddressStreet1: String? = null
    var billingAddressStreet2: String? = null
    var billingAddressCity: String? = null
    var billingAddressState: String? = null
    var billingAddressCountry: String? = null
    var billingAddressZipcode: String? = null

    @OneToOne
    var order: Order? = null
}