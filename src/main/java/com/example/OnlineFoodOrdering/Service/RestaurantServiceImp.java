package com.example.OnlineFoodOrdering.Service;

import com.example.OnlineFoodOrdering.dto.RestaurantDto;
import com.example.OnlineFoodOrdering.model.Address;
import com.example.OnlineFoodOrdering.model.Restaurant;
import com.example.OnlineFoodOrdering.model.User;
import com.example.OnlineFoodOrdering.repository.AddressRepository;
import com.example.OnlineFoodOrdering.repository.RestaurantRepository;
import com.example.OnlineFoodOrdering.repository.UserRepository;
import com.example.OnlineFoodOrdering.request.CreateRestaurantRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImp implements RestaurantService{
    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Override
    public Restaurant createdRestaurant(CreateRestaurantRequest request, User user) {
        Address address = addressRepository.save(request.getAddress());
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformation(request.getContactInformation());
        restaurant.setCuisineType(request.getCuisineType());
        restaurant.setDescription(request.getDescription());
        restaurant.setImages(request.getImages());
        restaurant.setName(request.getName());
        restaurant.setOpeningHours(request.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurant) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        if(restaurant.getCuisineType() != null){
            restaurant.setCuisineType(updateRestaurant.getOpeningHours());
        }
        if(restaurant.getDescription() != null){
            restaurant.setDescription(updateRestaurant.getDescription());
        }
        if(restaurant.getName() != null){
            restaurant.setName(updateRestaurant.getName());
        }

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);
        return restaurant;
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> otp = restaurantRepository.findById(id);
        if(otp.isEmpty()){
            throw new Exception("Data not found");
        }
        return otp.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long id) throws Exception {
        Restaurant restaurant = restaurantRepository.findByOwnerId(id);
        if (restaurant == null){
            throw new Exception("Data not found");
        }
        return restaurant;
    }

    @Override
    public RestaurantDto addToFavorite(Long restaurantId, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        RestaurantDto dto = new RestaurantDto();
        dto.setTitle(restaurant.getName());
        dto.setDescription(restaurant.getDescription());
        dto.setImages(restaurant.getImages());
        dto.setId(restaurantId);

        boolean isFavorite = false;
        List<RestaurantDto> favorites = user.getFavorite();
        for (RestaurantDto favorite : favorites){
            if(favorite.getId().equals(restaurantId)){
                isFavorite = true;
                break;
            }
        }
        if(isFavorite){
            favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
        }else {
            favorites.add(dto);
        }

        userRepository.save(user);
        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
