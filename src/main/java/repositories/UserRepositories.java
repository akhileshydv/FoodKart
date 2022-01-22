package repositories;

import entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepositories implements BaseDao<String, User>{
    private static Map<String, User> userDB = new HashMap<>();

    @Override
    public User create(User entity) {

        userDB.put(entity.getName(), entity);
        return entity;
    }

    @Override
    public User find(String type) {
        return userDB.get(type);
    }

    @Override
    public User update(User entity) {
        userDB.put(entity.getName(), entity);
        return entity;
    }

    @Override
    public List<User> findALl() {
        List<User> users = new ArrayList<>();
        for(Map.Entry entry : userDB.entrySet()){
            users.add((User) entry.getValue());
        }
        return users;
    }
}
