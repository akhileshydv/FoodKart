package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import repositories.RestaurantRepositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Setter
@Getter
@AllArgsConstructor
public class Restaurant extends BaseEntity{
    RestaurantRepositories restaurantRepositories = new RestaurantRepositories();
    String Name;
    List<String> serviceablePincode;
    List<Item> foodItems;
    Map<String, Long> inventory;
    List<Review> reviews;
    List<Order> orders;
    Long rating;

    public Restaurant(String name, List<String> serviceablePincode, List<Item> foodItems) {
        Name = name;
        this.serviceablePincode = serviceablePincode;
        this.foodItems = foodItems;
        orders = new ArrayList<>();
        inventory = new HashMap<>();
        reviews = new ArrayList<>();
        for(Item item: foodItems){
            inventory.put(item.getName(), item.getQty());
        }
    }

    public Item updateItem(Long qty){
        Item item = foodItems.get(0);
        Long updatedQty =  this.inventory.get(item.getName()) + qty;
        item.setQty(updatedQty);
        foodItems.get(0).setQty(updatedQty);
        inventory.put(item.getName(), item.getQty());
        restaurantRepositories.update(this);
        return item;
    }

    public Double getRating(){
        Double sum = 0.0;
        for (Review review : reviews){
            sum+= review.getRating();
        }
        return sum/reviews.size();
    }

    public Order addOrder(String userName, String restaurantName, List<Item> items){
        for(Item item: items){
            inventory.put(item.getName(), inventory.get(item.getName())- item.getQty());
        }
        Order order = new Order(userName, items);
        order.setTotalPrice(order.getTotalPrice());
        orders.add(order);
        restaurantRepositories.update(this);
        return order;
    }

    public Review addReview(String userName, Long rating){
        Review review = new Review(rating, userName);
        reviews.add(review);
        restaurantRepositories.update(this);
        return review;
    }
}
