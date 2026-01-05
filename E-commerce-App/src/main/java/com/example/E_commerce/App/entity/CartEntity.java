package com.example.E_commerce.App.entity;

import jakarta.persistence.*;

@Entity
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  int id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductEntity product;



    public CartEntity() {
    }

    public CartEntity(User user, ProductEntity product) {
        this.user = user;
        this.product = product;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "CartEntity{" +
                "id=" + id +
                ", user=" + user +
                ", product=" + product +
                '}';
    }
}
