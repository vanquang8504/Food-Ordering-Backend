package com.example.OnlineFoodOrdering.repository;

import com.example.OnlineFoodOrdering.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findByRestaurantId(Long restaurantId);

    @Query("SELECT f FROM Food f WHERE f.name like %:keyword% or f.foodCategory.name like %:keyword%")
    List<Food> searchFood(@Param("keyword") String keyword);
}
