package me.itslucas.bookstore.domain

import grails.gorm.annotation.*;

@Entity
class Customer {
    String name;
    String address;
    String tel;
    String fax;
    String zipCode;
    String bank;
    String account;

    static constraints = {
        name(maxSize: 15)
        tel(maxSize: 15)
        address(maxSize: 60)
        zipCode(maxSize: 8)
        fax(maxSize: 15)
        bank(maxSize: 15)
        account(maxSize: 20)
    }

    String toString() {
        return "${name}:${tel}";
    }
}
