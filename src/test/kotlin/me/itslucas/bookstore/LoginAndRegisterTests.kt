package me.itslucas.bookstore

import com.fasterxml.jackson.databind.ObjectMapper
import me.itslucas.bookstore.domain.UserLoginDomain
import me.itslucas.bookstore.domain.UserTestDomain
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import java.util.UUID

@SpringBootTest
@AutoConfigureMockMvc

class LoginAndRegisterTests {
    @Autowired
    private val mockMvc: MockMvc? = null

    private var useruuid: String? = null
    fun asJsonString(obj: Any?): String? {
        return try {
            ObjectMapper().writeValueAsString(obj)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @Test
    @Order(1)
    fun randomRegisterTest() {
        useruuid = UUID.randomUUID().toString().replace("-", "")
        this.mockMvc?.perform(
            post("/users/record")
                .contentType(APPLICATION_JSON)
                .content(
                    asJsonString(
                        UserTestDomain(
                            useruuid,
                            "123456",
                            "example@example.com",
                            "12312312312"
                        )
                    )!!
                )
        )
            ?.andExpect(status().is2xxSuccessful)
    }

    @Test
    @Order(2)
    fun defaultLoginTest() {
        this.mockMvc?.perform(
            post("/login")
                .contentType(APPLICATION_JSON)
                .content("{\"username\":\"lucas\",\"password\":\"990924\"}")
        )
            ?.andExpect(status().is2xxSuccessful)
            ?.andExpect(header().exists("token"))
    }
}