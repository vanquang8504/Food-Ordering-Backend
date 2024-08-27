package com.example.OnlineFoodOrdering.Service;

import com.example.OnlineFoodOrdering.model.IngredientCategory;
import com.example.OnlineFoodOrdering.model.IngredientsItem;

import java.util.List;

public interface IngredientsService {
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception;

    public IngredientCategory findIngredientCategoryId(Long id) throws Exception;

    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;

    public IngredientsItem createIngredientItem(Long restaurantId,
                                                String ingredientName,
                                                Long categoryId) throws Exception;

    public List<IngredientsItem> findRestaurantIngredients(Long restaurantId) throws Exception;

    public IngredientsItem updateStock(Long id) throws Exception;
}
