package me.itslucas.bookstore.service;

import me.itslucas.bookstore.domain.UserShipping;

public interface UserShippingService {
    UserShipping findById(Long id);

    void removeById(Long id);
}
