package com.edu.eci.ieti.service.impl;

import com.edu.eci.ieti.entities.User;
import com.edu.eci.ieti.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceHashMap implements UserService {
    private HashMap<String,User> users = new HashMap<>();

    @Override
    public User create(User user) {
        if (users.containsKey(user.getId())){
            return null;
        }
        users.put(user.getId(),user);
        return user;
    }

    @Override
    public User findById(String id) {
        return users.get(id);
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers= new ArrayList<>();
        for (String id: users.keySet()) {
            allUsers.add(users.get(id));
        }
        return allUsers;
    }

    @Override
    public void deleteById(String id) {
        if (users.containsKey(id)){
            users.remove(id);
        }
    }

    @Override
    public User update(User user, String userId) {
        if (!users.containsKey(userId)){
            return null;
        }
        deleteById(userId);
        return create(user);
    }
}
