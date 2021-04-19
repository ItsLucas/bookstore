package me.itslucas.bookstore.service;

import me.itslucas.bookstore.domain.User;
import me.itslucas.bookstore.domain.UserBilling;
import me.itslucas.bookstore.domain.UserPayment;
import me.itslucas.bookstore.domain.UserShipping;
import me.itslucas.bookstore.domain.security.PasswordResetToken;
import me.itslucas.bookstore.domain.security.UserRole;

import java.util.Set;

public interface UserService {
    PasswordResetToken getPasswordResetToken(final String token);

    void createPasswordResetTokenForUser(final User user, final String token);

    User findByUsername(String username);

    User findByEmail(String email);

    User findById(Long id);

    User createUser(User user) throws Exception;

    User save(User user);

    void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user);

    void updateUserShipping(UserShipping userShipping, User user);

    void setUserDefaultPayment(Long userPaymentId, User user);

    void setUserDefaultShipping(Long userShippingId, User user);
}
