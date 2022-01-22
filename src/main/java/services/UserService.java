package services;

import entities.Item;
import entities.Order;
import entities.Restaurant;
import entities.User;
import enums.OrderBy;

import java.util.List;

public interface UserService {
    User registerUser(String name, String phoneNo, String pinCode);
    void showRestaurant(OrderBy type);
    Order placeOrder(String userName, String restaurantName, Long qty);
    void createReview(String userName, String restaurantName, Long rating);
}
