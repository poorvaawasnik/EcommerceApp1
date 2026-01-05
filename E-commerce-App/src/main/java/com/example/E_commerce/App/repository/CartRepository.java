package com.example.E_commerce.App.repository;

import com.example.E_commerce.App.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {


}
