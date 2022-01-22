package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Order extends BaseEntity{
    String restaurantName;
    List<Item> orderItems;
    Double totalPrice;
    String userName;

    public Order(String restaurantName, List<Item> orderitems) {
        this.restaurantName = restaurantName;
        this.orderItems = orderitems;
        Double price = 0.0;
        for(Item item:orderitems){
            price+=item.getPrice() * item.getQty();
        }
        totalPrice = price;
    }
}
