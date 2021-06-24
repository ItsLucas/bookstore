package me.itslucas.bookstore.repository

import me.itslucas.bookstore.domain.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long>