package com.example.OnlineFoodOrdering.repository;

import com.example.OnlineFoodOrdering.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
