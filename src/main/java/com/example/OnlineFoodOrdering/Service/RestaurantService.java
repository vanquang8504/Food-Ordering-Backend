package com.example.OnlineFoodOrdering.Service;

import com.example.OnlineFoodOrdering.dto.RestaurantDto;
import com.example.OnlineFoodOrdering.model.Restaurant;
import com.example.OnlineFoodOrdering.model.User;
import com.example.OnlineFoodOrdering.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {

    public Restaurant createdRestaurant(CreateRestaurantRequest request, User user);

    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurant) throws Exception;

    public Restaurant deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurant();

    public List<Restaurant> searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Long id) throws Exception;

    public Restaurant getRestaurantByUserId(Long id) throws Exception;

    public RestaurantDto addToFavorite(Long restaurantId, User user) throws Exception;

    public Restaurant updateRestaurantStatus(Long id) throws Exception;


}
