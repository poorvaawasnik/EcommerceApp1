package com.example.E_commerce.App.repository;

import com.example.E_commerce.App.entity.CartEntity;
import com.example.E_commerce.App.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {
    @Query("select c from CartEntity c where c.user =?1")
    public List<CartEntity> findByUser(User user);


}
