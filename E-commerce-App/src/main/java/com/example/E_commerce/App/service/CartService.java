package com.example.E_commerce.App.service;

import com.example.E_commerce.App.entity.CartEntity;
import com.example.E_commerce.App.entity.ProductEntity;
import com.example.E_commerce.App.entity.User;
import com.example.E_commerce.App.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository repository;
    public void addCart(User user, ProductEntity product)
    {
        CartEntity cart=new CartEntity(user, product);
        repository.save(cart);
    }
    public List<CartEntity> getCartByuser(User user){
        return repository.findByUser(user);
    }
}
