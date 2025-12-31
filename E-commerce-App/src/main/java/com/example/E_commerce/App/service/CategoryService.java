package com.example.E_commerce.App.service;

import com.example.E_commerce.App.entity.CategoryEntity;
import com.example.E_commerce.App.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository repository;
    public void saveCategory(CategoryEntity entity)
    {
        repository.save(entity);
    }
    public List<CategoryEntity> getAllCategory(){
        return repository.findAll();
    }
    public void deleteCategory(int id){
        repository.deleteById(id);
    }
    public CategoryEntity getCategoryById(int id){
        return repository.findById(id).orElse(null);
    }
    public CategoryEntity getCategoryByName(String cname){
        return repository.findCategoryByName(cname);
    }
}
