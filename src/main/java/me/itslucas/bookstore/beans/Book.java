package me.itslucas.bookstore.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String author;
    private String description;
    private String type;
    private int quantity;
    private float price;
    private float discount;

    public Book(String name, String author, String description, String type, int quantity, float price, float discount) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }

    public Book() {

    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public float getDiscount() {
        return discount;
    }
}
