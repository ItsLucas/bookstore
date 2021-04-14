package me.itslucas.bookstore.controller;

import me.itslucas.bookstore.domain.Book;
import me.itslucas.bookstore.domain.User;
import me.itslucas.bookstore.service.BookService;
import me.itslucas.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;


}
