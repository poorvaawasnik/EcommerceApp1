package com.example.E_commerce.App.repository;

import com.example.E_commerce.App.entity.CategoryEntity;
import com.example.E_commerce.App.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
 @Query("select p  from ProductEntity p where p.category=?1")
public List<ProductEntity> findByCategory(CategoryEntity category);



}
