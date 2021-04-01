package me.itslucas.bookstore.repos;

import me.itslucas.bookstore.beans.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findAccountByUserName(String userName);

    Account findAccountById(Long Id);
}
