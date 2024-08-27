package com.example.OnlineFoodOrdering.repository;

import com.example.OnlineFoodOrdering.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
