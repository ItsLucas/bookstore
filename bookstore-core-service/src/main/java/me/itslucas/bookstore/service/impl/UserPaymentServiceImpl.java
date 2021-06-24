package me.itslucas.bookstore.service.impl;

import me.itslucas.bookstore.domain.UserPayment;
import me.itslucas.bookstore.repository.UserPaymentRepository;
import me.itslucas.bookstore.service.UserPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPaymentServiceImpl implements UserPaymentService {

    @Autowired
    private UserPaymentRepository userPaymentRepository;

    public UserPayment findById(Long id) {
        return userPaymentRepository.findById(id).get();
    }

    public void removeById(Long id) {
        userPaymentRepository.deleteById(id);
    }
}
