package com.mercadona.webapp.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Blob;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "discount")
    private int discount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate discountStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate discountEnd;

    @Column(name = "priceReduced")
    private float priceReduced;

    @Lob
    @Column(name = "image", nullable = true)
    private String image;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "creatorUserId")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public LocalDate getDiscountStart() {
        return discountStart;
    }

    public void setDiscountStart(LocalDate discountStart) {
        this.discountStart = discountStart;
    }

    public LocalDate getDiscountEnd() {
        return discountEnd;
    }

    public void setDiscountEnd(LocalDate discountEnd) {
        this.discountEnd = discountEnd;
    }

    public float getPriceReduced() {
        return priceReduced;
    }

    public void setPriceReduced(float priceReduced) {
        this.priceReduced = priceReduced;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
