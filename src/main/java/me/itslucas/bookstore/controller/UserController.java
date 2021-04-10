package me.itslucas.bookstore.controller;

import me.itslucas.bookstore.beans.Account;
import me.itslucas.bookstore.enums.Roles;
import me.itslucas.bookstore.repos.AccountRepository;
import me.itslucas.bookstore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserService userService;
    @GetMapping("/api/login")
    public boolean userLogin(@RequestParam(value="username") String username, @RequestParam(value="password") String password) {
        try {
            Account account = userService.findByUserName(username);
            if(account.getPassWord().equals(password)) {
                logger.info("Login Success: "+username);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping("/api/tests/account")
    public String addDefaultAccount() {
        Account account = new Account(10086L,"lucas","990924","17621979123","test", Roles.BOSS);
        userService.addNewAccount(account);
        return "Success";
    }
}
