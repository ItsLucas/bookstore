package me.itslucas.bookstore.repos;

import me.itslucas.bookstore.beans.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findById(Long Id);
    Account findByUserName(String userName);
}
