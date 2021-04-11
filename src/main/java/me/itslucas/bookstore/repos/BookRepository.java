package me.itslucas.bookstore.repos;

import me.itslucas.bookstore.beans.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book,Long> {
    Optional<Book> findBookByName(String name);
}
