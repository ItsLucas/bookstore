package me.itslucas.bookstore.beans;

import me.itslucas.bookstore.enums.Roles;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Account {
    @Id
    @GeneratedValue
    private Long Id;
    private String userName;
    private String passWord;
    private String phoneNumber;
    private String address;
    private Roles role;

    public Account(Long id, String userName, String passWord, String phoneNumber, String address, Roles role) {
        Id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }

    public Account() {

    }

    public Long getId() {
        return Id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public Roles getRole() {
        return role;
    }
}
