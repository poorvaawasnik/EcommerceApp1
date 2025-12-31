package com.example.E_commerce.App.repository;

import com.example.E_commerce.App.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {
    @Query("select c from CategoryEntity c where c.name=?1")
public CategoryEntity findCategoryByName(String name);
}
