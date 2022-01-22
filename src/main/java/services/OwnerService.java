package services;

import entities.Item;
import entities.Restaurant;

import java.util.List;

public interface OwnerService {
    Restaurant registerRestaurant(String name, List<String> pinCode, List<Item> items);
    Item updateQuantity(String name, Long qty);
}
