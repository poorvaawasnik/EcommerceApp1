package com.example.E_commerce.App.service;

import com.example.E_commerce.App.entity.CartEntity;
import com.example.E_commerce.App.entity.ProductEntity;
import com.example.E_commerce.App.entity.User;
import com.example.E_commerce.App.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    CartRepository repository;
    public void addCart(User user, ProductEntity product)
    {
        CartEntity cart=new CartEntity(user, product);
        repository.save(cart);
    }
}
