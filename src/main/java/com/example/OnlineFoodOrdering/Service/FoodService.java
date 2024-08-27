package com.example.OnlineFoodOrdering.Service;

import com.example.OnlineFoodOrdering.model.Category;
import com.example.OnlineFoodOrdering.model.Food;
import com.example.OnlineFoodOrdering.model.Restaurant;
import com.example.OnlineFoodOrdering.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {
    public Food createFood(CreateFoodRequest request, Category category, Restaurant restaurant);

    void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantFood(Long restaurantId,
                                        boolean isVegetarain,
                                        boolean isNonveg,
                                        boolean isSeasonal,
                                        String foodCategory);
    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId) throws Exception;

    public Food updateAvailabilityStatus(Long foodId) throws Exception;
}
