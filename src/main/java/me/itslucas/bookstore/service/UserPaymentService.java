package me.itslucas.bookstore.service;

import me.itslucas.bookstore.domain.UserPayment;

public interface UserPaymentService {
    UserPayment findById(Long id);

    void removeById(Long id);
}
