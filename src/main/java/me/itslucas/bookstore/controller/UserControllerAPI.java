package me.itslucas.bookstore.controller;

import me.itslucas.bookstore.beans.Account;
import me.itslucas.bookstore.enums.Roles;
import me.itslucas.bookstore.repos.AccountRepository;
import me.itslucas.bookstore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api")
public class UserControllerAPI {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    MessageDigest md5digest;
    private UserService userService;
    @Autowired
    public UserControllerAPI(UserService userService) throws NoSuchAlgorithmException {
        this.userService=userService;
        this.md5digest = MessageDigest.getInstance("MD5");
    }

    @GetMapping("/login")
    public boolean userLogin(@RequestParam(value="username") String username, @RequestParam(value="password") String password) {
        try {
            Account account = userService.findByUserName(username);
            String encrypted = DatatypeConverter.printHexBinary(md5digest.digest(password.getBytes()));
            if(encrypted.equals(account.getPassWord())) {
                logger.info("Login Success: "+username);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping("/tests/account")
    @ResponseStatus(HttpStatus.CREATED)
    public String addDefaultAccount() {
        Account account = new Account("lucas","990924","17621979123","test", Roles.BOSS,23333);
        account.setPassWord(DatatypeConverter.printHexBinary(md5digest.digest(account.getPassWord().getBytes())));
        userService.addNewAccount(account);
        return "Success";
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean registerAccount(@RequestBody Account account) {
        if(userService.accountExistsByUserName(account.getUserName())) {
            return false;
        }
        else {
            account.setPassWord(DatatypeConverter.printHexBinary(md5digest.digest(account.getPassWord().getBytes())));
            userService.addNewAccount(account);
            return true;
        }
    }
}
