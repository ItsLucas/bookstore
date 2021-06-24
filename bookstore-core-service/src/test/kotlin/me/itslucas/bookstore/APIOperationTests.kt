package me.itslucas.bookstore

import org.springframework.boot.test.context.SpringBootTest
import com.fasterxml.jackson.databind.ObjectMapper
import me.itslucas.bookstore.domain.UserLoginDomain
import me.itslucas.bookstore.domain.UserTestDomain
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import java.util.UUID

@SpringBootTest
@AutoConfigureMockMvc
class APIOperationTests {
    @Autowired
    private val mockMvc: MockMvc? = null

    @Test
    fun viewCartTest() {
        this.mockMvc?.perform(
            get("/api/cart")
                .contentType(APPLICATION_JSON)
                .content("blah")
        )?.andExpect(status().is4xxClientError)
    }

    @Test
    fun addCartTest() {
        this.mockMvc?.perform(
            post("/api/addcart")
                .contentType(APPLICATION_JSON)
                .content("blah")
        )?.andExpect(status().is4xxClientError)
    }

    @Test
    fun removeCartTest() {
        this.mockMvc?.perform(
            get("/api/removecart")
                .contentType(APPLICATION_JSON)
                .content("blah")
        )?.andExpect(status().is4xxClientError)
    }

    @Test
    fun createOrderTest() {
        this.mockMvc?.perform(
            post("/api/placeorder")
                .contentType(APPLICATION_JSON)
                .content("blah")
        )?.andExpect(status().is4xxClientError)
    }

    @Test
    fun getProductTest() {
        this.mockMvc?.perform(
            get("/api/index")
                .contentType(APPLICATION_JSON)
                .content("blah")
        )?.andExpect(status().is4xxClientError)
    }
}