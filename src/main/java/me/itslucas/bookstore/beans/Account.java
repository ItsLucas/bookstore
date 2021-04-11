package me.itslucas.bookstore.beans;

import me.itslucas.bookstore.enums.Roles;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String userName;
    private String passWord;
    private String phoneNumber;
    private String address;
    private Roles role;
    private int balance;
    public Account(Long Id, String userName, String passWord, String phoneNumber, String address, Roles role, int balance) {
        this.Id = Id;
        this.userName = userName;
        this.passWord = passWord;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
        this.balance=balance;
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

    public int getBalance() {return balance;}
}
