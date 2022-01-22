package services.impl;

import entities.Item;
import entities.Order;
import entities.Restaurant;
import entities.User;
import enums.OrderBy;
import repositories.RestaurantRepositories;
import repositories.UserRepositories;
import services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    UserRepositories userRepositories = new UserRepositories();
    RestaurantRepositories restaurantRepositories = new RestaurantRepositories();
    @Override
    public User registerUser(String name, String phoneNo, String pinCode) {
        User user = new User(name, pinCode, phoneNo);
        return userRepositories.create(user);
    }

    @Override
    public void showRestaurant(OrderBy type) {
        List<Restaurant> restaurants = null;
        switch (type){
            case PRICE :
                restaurants = restaurantRepositories.findALl().stream().sorted((r1, r2) -> (int) (r2.getFoodItems().get(0).getPrice() -r1.getFoodItems().get(0).getPrice())).collect(Collectors.toList());
                break;
            case RATING:;
                restaurants = restaurantRepositories.findALl().stream().sorted((r1,r2) -> (int) (r2.getRating() - r1.getRating())).collect(Collectors.toList());
                break;
        }
        System.out.println("Listing Restaurant...");
        for(Restaurant restaurant : restaurants){
            Item item1 = restaurant.getFoodItems().get(0);
            System.out.println(restaurant.getName() + " " +  item1.getName() + " " + restaurant.getInventory().get(item1.getName()));
        }
    }

    @Override
    public Order placeOrder(String userName, String restaurantName, Long quantity) {
        System.out.println("Placing Order...");
        User user = userRepositories.find(userName);
        List<Restaurant> restaurants = restaurantRepositories.findALl().stream().filter(r -> r.getServiceablePincode().contains(user.getPinCode())).collect(Collectors.toList());
        if(restaurants.size() == 0){
            System.out.println("Order Cant be placed");
            return null;
        }
        List<Restaurant> eligible = restaurants.stream().filter(r -> r.getInventory().get(r.getFoodItems().get(0).getName()) > quantity && r.getName().equals(restaurantName)).collect(Collectors.toList());
        if(eligible.size() == 0){
            System.out.println("Order Cant be placed");
            return null;
        }
        Restaurant selected  = eligible.get(0);
        Item foodItem = selected.getFoodItems().get(0);
        Item item = new Item(foodItem.getName(),quantity, foodItem.getPrice());
        List<Item> items = new ArrayList<>();
        items.add(item);
        System.out.println("Order Placed Successfully");
        return selected.addOrder(userName, restaurantName, items);
    }

    @Override
    public void createReview(String userName, String restaurantName, Long rating) {
        Restaurant restaurant = restaurantRepositories.find(restaurantName);
        restaurant.addReview(userName, rating);
    }
}
