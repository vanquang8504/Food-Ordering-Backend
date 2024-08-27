package com.example.OnlineFoodOrdering.controller;

import com.example.OnlineFoodOrdering.Service.CategoryService;
import com.example.OnlineFoodOrdering.Service.UserService;
import com.example.OnlineFoodOrdering.model.Category;
import com.example.OnlineFoodOrdering.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final UserService userService;

    @PostMapping("/admin/categories")
    public ResponseEntity<Category> createCategory(
            @RequestBody Category category,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Category createCategory = categoryService.createCategory(category.getName(), user.getId());
        return new ResponseEntity<>(createCategory, HttpStatus.OK);
    }

    @GetMapping("/categories/restaurant/{id}")
    public ResponseEntity<List<Category>> getRestaurantCategory(
            @PathVariable Long id,
//            @RequestBody Category category,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Category> categories = categoryService.findCategoryRestaurantId(user.getId());
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
