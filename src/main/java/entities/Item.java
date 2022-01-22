package entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item extends BaseEntity{
    String name;
    Long qty;
    Double price;

    public Item(String name, Long qty, Double price) {
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    public Item(String name, Long qty) {
        this.name = name;
        this.qty = qty;
    }
}
