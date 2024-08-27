package com.example.OnlineFoodOrdering.controller;

import com.example.OnlineFoodOrdering.Service.FoodService;
import com.example.OnlineFoodOrdering.Service.RestaurantService;
import com.example.OnlineFoodOrdering.Service.UserService;
import com.example.OnlineFoodOrdering.model.Food;
import com.example.OnlineFoodOrdering.model.Restaurant;
import com.example.OnlineFoodOrdering.model.User;
import com.example.OnlineFoodOrdering.request.CreateFoodRequest;
import com.example.OnlineFoodOrdering.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/foods")
public class AdminFoodController {
    private final FoodService foodService;
    private final UserService userService;
    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Food> createFood(
            @RequestBody CreateFoodRequest request,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(request.getRestaurantId());
        Food food = foodService.createFood(request, request.getCategory(), restaurant);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        foodService.deleteFood(id);
        MessageResponse response = new MessageResponse();
        response.setMessage("Food deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvaibilityStatus(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Food food = foodService.updateAvailabilityStatus(id);

        return new ResponseEntity<>(food, HttpStatus.OK);
    }
}
