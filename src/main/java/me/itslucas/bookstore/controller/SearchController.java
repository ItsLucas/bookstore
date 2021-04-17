package me.itslucas.bookstore.controller;

import me.itslucas.bookstore.service.BookService;
import me.itslucas.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SearchController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;


}
