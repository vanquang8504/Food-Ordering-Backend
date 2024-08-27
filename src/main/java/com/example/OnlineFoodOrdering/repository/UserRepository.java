package com.example.OnlineFoodOrdering.repository;

import com.example.OnlineFoodOrdering.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String username);
}
