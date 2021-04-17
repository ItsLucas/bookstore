package me.itslucas.bookstore.domain.security

import me.itslucas.bookstore.domain.User
import java.util.*
import javax.persistence.*

@Entity
class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    var token: String? = null

    @OneToOne(targetEntity = User::class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    var user: User? = null
    var expiryDate: Date? = null

    constructor() {}
    constructor(token: String?, user: User?) : super() {
        this.token = token
        this.user = user
        expiryDate = calculateExpiryDate(expiration)
    }

    private fun calculateExpiryDate(expiryTimeInMinutes: Int): Date {
        val cal = Calendar.getInstance()
        cal.timeInMillis = Date().time
        cal.add(Calendar.MINUTE, expiryTimeInMinutes)
        return Date(cal.time.time)
    }

    fun updateToken(token: String?) {
        this.token = token
        expiryDate = calculateExpiryDate(expiration)
    }

    override fun toString(): String {
        return ("PasswordResetToken [id=" + id + ", token=" + token + ", user=" + user + ", expiryDate=" + expiryDate
                + "]")
    }

    companion object {
        const val expiration = 60 * 24
    }
}