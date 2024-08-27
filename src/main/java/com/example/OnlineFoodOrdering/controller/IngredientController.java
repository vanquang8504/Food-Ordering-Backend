package com.example.OnlineFoodOrdering.controller;

import com.example.OnlineFoodOrdering.Service.IngredientsService;
import com.example.OnlineFoodOrdering.model.IngredientCategory;
import com.example.OnlineFoodOrdering.model.IngredientsItem;
import com.example.OnlineFoodOrdering.request.IngredientCategoryRequest;
import com.example.OnlineFoodOrdering.request.IngredientItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/ingredients")
public class IngredientController {
    private final IngredientsService ingredientsService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(
            @RequestBody IngredientCategoryRequest request
            ) throws Exception {
        IngredientCategory item = ingredientsService.createIngredientCategory(request.getName(),request.getRestaurantId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PostMapping("/")
    public ResponseEntity<IngredientsItem> createIngredientItem(
            @RequestBody IngredientItemRequest request
    ) throws Exception {
        IngredientsItem item = ingredientsService.createIngredientItem(request.getRestaurantId(), request.getName(),request.getCategoryId());
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PutMapping("/stoke/{id}")
    public ResponseEntity<IngredientsItem> updateIngredientStock(
            @PathVariable Long id
    ) throws Exception {
        IngredientsItem item = ingredientsService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> getRestaurantIngredient(
            @PathVariable Long id
    ) throws Exception {
        List<IngredientsItem> items = ingredientsService.findRestaurantIngredients(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/restaurant/category/{id}")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(
            @PathVariable Long id
    ) throws Exception {
        List<IngredientCategory> items = ingredientsService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

}
