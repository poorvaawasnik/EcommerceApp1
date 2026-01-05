package com.example.E_commerce.App.service;

import com.example.E_commerce.App.entity.CategoryEntity;
import com.example.E_commerce.App.entity.ProductEntity;
import com.example.E_commerce.App.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository repository;
    public  void saveProduct(ProductEntity entity)
    {
        repository.save(entity);
    }
    public List<ProductEntity> getAllProduct(){
        return repository.findAll();
    }
    public List<ProductEntity> getLatestProduct(){
        return  repository.findAll().reversed().subList(0,3);
    }

    public List<ProductEntity> getProductByCategory( CategoryEntity category)
    {
        return repository.findByCategory(category);
    }
    public ProductEntity getProductById(int id){
        return repository.findById(id).orElse(null);
    }
}
