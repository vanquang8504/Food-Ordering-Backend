package com.example.OnlineFoodOrdering.controller;

import com.example.OnlineFoodOrdering.Service.FoodService;
import com.example.OnlineFoodOrdering.Service.RestaurantService;
import com.example.OnlineFoodOrdering.Service.UserService;
import com.example.OnlineFoodOrdering.model.Food;
import com.example.OnlineFoodOrdering.model.Restaurant;
import com.example.OnlineFoodOrdering.model.User;
import com.example.OnlineFoodOrdering.repository.FoodRepository;
import com.example.OnlineFoodOrdering.request.CreateFoodRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/foods")
public class FoodController {
    private final FoodService foodService;
    private final UserService userService;
    private final RestaurantService restaurantService;
    private final FoodRepository foodRepository;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(
            @RequestParam String name,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Food> foods = foodService.searchFood(name);
        return new ResponseEntity<>(foods, HttpStatus.CREATED);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(
            @RequestParam(required = false) boolean vegetarian,
            @RequestParam(required = false) boolean seasonal,
            @RequestParam(required = false) boolean nonveg,
            @RequestParam(required = false) String food_category,
            @PathVariable Long restaurantId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Food> foods = foodService.getRestaurantFood(restaurantId, vegetarian, nonveg, seasonal, food_category);

        return new ResponseEntity<>(foods, HttpStatus.CREATED);
    }

    @GetMapping("/getall")
    public List<?> getAllFood(){
        return foodRepository.findAll();
    }
}
