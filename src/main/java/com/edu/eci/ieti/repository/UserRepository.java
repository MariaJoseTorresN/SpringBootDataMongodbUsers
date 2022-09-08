package com.edu.eci.ieti.repository;

import com.edu.eci.ieti.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findUsersWithNameOrLastNameLike(String queryText);

    List<User> findUsersCreatedAfter(Date startDate);
}