package me.itslucas.bookstore.service;

import me.itslucas.bookstore.beans.Book;
import me.itslucas.bookstore.repos.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    /*
    @param Book
    @return true if success
     */
    public boolean addBook(Book book) {
        if(repository.findBookByName(book.getName()).isPresent()) {
            return false;
        }
        else {
            repository.save(book);
            return true;
        }
    }

    public Optional<Book> getBookByName(String name) {
        return repository.findBookByName(name);
    }
}
