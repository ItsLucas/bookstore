package me.itslucas.bookstore.controller;

import me.itslucas.bookstore.domain.BillingAddress;
import me.itslucas.bookstore.domain.Payment;
import me.itslucas.bookstore.domain.ShippingAddress;
import me.itslucas.bookstore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CheckoutController {

    private final ShippingAddress shippingAddress = new ShippingAddress();
    private final BillingAddress billingAddress = new BillingAddress();
    private final Payment payment = new Payment();

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShippingAddressService shippingAddressService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserPaymentService userPaymentService;

    @Autowired
    private OrderService orderService;

}
