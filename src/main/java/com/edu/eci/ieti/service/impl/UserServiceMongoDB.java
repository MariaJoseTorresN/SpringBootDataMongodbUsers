package com.edu.eci.ieti.service.impl;

import com.edu.eci.ieti.entities.User;
import com.edu.eci.ieti.repository.UserRepository;
import com.edu.eci.ieti.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceMongoDB implements UserService {

    private final UserRepository userRepository;

    public UserServiceMongoDB(@Autowired UserRepository userRepository )
    {
        this.userRepository = userRepository;
    }

    @Override
    public User create( User user ) {
        return userRepository.insert(user);
    }

    @Override
    public User findById( String id ) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteById( String id ) {
        boolean flag = false;
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            flag = true;
        }
        return flag;
    }

    @Override
    public User update(User user, String id ) {
        userRepository.save(user);
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> findUsersWithNameOrLastNameLike(String queryText) {
        return userRepository.findUsersWithNameOrLastNameLike(queryText);
    }

    @Override
    public List<User> findUsersCreatedAfter(Date startDate) {
        return userRepository.findUsersCreatedAfter(startDate);
    }
}