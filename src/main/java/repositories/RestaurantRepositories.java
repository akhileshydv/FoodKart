package repositories;

import entities.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantRepositories implements BaseDao<String, Restaurant >{

    private static Map<String, Restaurant> restaurantDB = new HashMap<>();
    @Override
    public Restaurant create(Restaurant entity) {

        restaurantDB.put(entity.getName(), entity);
        return entity;
    }

    @Override
    public Restaurant find(String type) {
        return restaurantDB.get(type);
    }

    @Override
    public Restaurant update(Restaurant entity) {
        restaurantDB.put(entity.getName(), entity);
        return entity;
    }

    @Override
    public List<Restaurant> findALl() {
        List<Restaurant> restaurants = new ArrayList<>();
        for(Map.Entry entry : restaurantDB.entrySet()){
            restaurants.add((Restaurant) entry.getValue());
        }
        return restaurants;
    }


}
