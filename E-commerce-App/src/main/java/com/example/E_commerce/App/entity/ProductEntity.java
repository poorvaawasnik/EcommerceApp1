package com.example.E_commerce.App.entity;

import jakarta.persistence.*;

@Entity
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    private int  price;
    private String detail;
    private String image;
    @ManyToOne
    @JoinColumn(name="category_id")
    private CategoryEntity category;

    public ProductEntity(String name, int price, String detail, String image, CategoryEntity category) {
        this.name = name;
        this.price = price;
        this.detail = detail;
        this.image = image;
        this.category = category;
    }

    public ProductEntity() {
    }


    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", detail='" + detail + '\'' +
                ", image='" + image + '\'' +
                ", category=" + category +
                '}';
    }
}
