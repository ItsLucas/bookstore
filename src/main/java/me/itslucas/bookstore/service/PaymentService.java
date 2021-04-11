package me.itslucas.bookstore.service;

import me.itslucas.bookstore.domain.Payment;
import me.itslucas.bookstore.domain.UserPayment;

public interface PaymentService {
    Payment setByUserPayment(UserPayment userPayment, Payment payment);
}
