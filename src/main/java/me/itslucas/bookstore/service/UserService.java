package me.itslucas.bookstore.service;

import me.itslucas.bookstore.beans.Account;
import me.itslucas.bookstore.repos.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private AccountRepository accountRepository;


    private boolean existsById(Long id) {
        return accountRepository.existsById(id);
    }

    public Account findById(Long id) throws Exception {
        Optional<Account> account = accountRepository.findById(id);
        if(!account.isPresent()) {
            throw new Exception("Account not found");
        }
        else return account.get();
    }
    public Account findByUserName(String userName) throws Exception {
        Account account = (Account) accountRepository.findByUserName(userName);
        if(account==null) {
            throw new Exception("Account not found");
        }
        else return account;
    }
    public List<Account> findAll() {
        List<Account> contacts = new ArrayList<>();
        accountRepository.findAll().forEach(contacts::add);
        return contacts;
    }

    public Account addNewAccount(Account account) {
        return accountRepository.save(account);
    }

    public void update(Account account) throws Exception{
        if(!existsById(account.getId())) {
            throw new Exception("No such account!");
        }
        else {
            accountRepository.save(account);
        }
    }

    public void deleteById(Long id){
        accountRepository.deleteById(id);
    }

    public Long count() {
        return accountRepository.count();
    }
}
