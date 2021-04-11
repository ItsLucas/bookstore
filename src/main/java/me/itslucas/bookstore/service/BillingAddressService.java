package me.itslucas.bookstore.service;

import me.itslucas.bookstore.domain.BillingAddress;
import me.itslucas.bookstore.domain.UserBilling;

public interface BillingAddressService {
    BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress);
}
