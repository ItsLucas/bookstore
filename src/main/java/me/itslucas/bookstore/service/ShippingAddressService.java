package me.itslucas.bookstore.service;

import me.itslucas.bookstore.domain.ShippingAddress;
import me.itslucas.bookstore.domain.UserShipping;

public interface ShippingAddressService {
    ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
}
