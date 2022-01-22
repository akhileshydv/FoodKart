package services.impl;

import entities.Item;
import entities.Restaurant;
import repositories.RestaurantRepositories;
import services.OwnerService;

import java.util.List;

public class OwnerServiceImpl implements OwnerService {
    RestaurantRepositories restaurantRepositories = new RestaurantRepositories();
    @Override
    public Restaurant registerRestaurant(String name, List<String> pinCodes, List<Item> items) {
        Restaurant restaurant = new Restaurant(name, pinCodes, items);
        return restaurantRepositories.create(restaurant);
    }

    @Override
    public Item updateQuantity(String name, Long qty) {
        System.out.println("Updating Quantity..");
        Restaurant restaurant = restaurantRepositories.find(name);
        return restaurant.updateItem(qty);
    }
}
