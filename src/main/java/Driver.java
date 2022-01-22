import entities.Item;
import entities.User;
import enums.OrderBy;
import repositories.RestaurantRepositories;
import repositories.UserRepositories;
import services.OwnerService;
import services.UserService;
import services.impl.OwnerServiceImpl;
import services.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Driver {
    public static void main(String args[]){
        UserRepositories userRepositories = new UserRepositories();
        RestaurantRepositories restaurantRepositories = new RestaurantRepositories();
        UserService userService = new UserServiceImpl();

        //create a user
        User user = userService.registerUser("Pralove", "123", "HSR");
        userRepositories.findALl().forEach(user1 -> System.out.println(user.getName() + " " + user.getPinCode() + " " + user.getPhoneNo()));

        //create a restaurants
        OwnerService ownerService = new OwnerServiceImpl();
        List<String> pinCodes = new ArrayList<>(Arrays.asList("BTM", "HSR"));
        Item item = new Item("Ni Thali", 5L, 100.0);
        List<Item> items = new ArrayList<>(Arrays.asList(item));
        ownerService.registerRestaurant("Food Court-1", pinCodes, items);
        pinCodes = new ArrayList<>(Arrays.asList("BTM"));
        item = new Item("Burger", 3L, 120.0);
        items = new ArrayList<>(Arrays.asList(item));
        ownerService.registerRestaurant("Food Court-2", pinCodes, items);
        pinCodes = new ArrayList<>(Arrays.asList("HSR"));
        item = new Item("Si Thali", 8L, 150.0);
        items = new ArrayList<>(Arrays.asList(item));
        ownerService.registerRestaurant("Food Court-3", pinCodes, items);

        userService.showRestaurant(OrderBy.PRICE);

        userService.placeOrder("Pralove",  "Food Court-1" , 2L);

        userService.showRestaurant(OrderBy.PRICE);

        ownerService.updateQuantity("Food Court-1", 2L);

        userService.showRestaurant(OrderBy.PRICE);

        ownerService.updateQuantity("Food Court-2", 3L);

        userService.showRestaurant(OrderBy.PRICE);

        userService.placeOrder("Pralove",  "Food Court-3" , 2L);

        userService.showRestaurant(OrderBy.PRICE);


    }
}
