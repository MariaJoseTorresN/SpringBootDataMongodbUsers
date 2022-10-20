package com.edu.eci.ieti.repository;

import com.edu.eci.ieti.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{$or: [{'name': {$regex: ?0, $options:'i'}}, {'lastName': {$regex: ?0, $options:'i'}}]}")
    List<User> findUsersWithNameOrLastNameLike(String queryText);

    @Query("{'createdAt' : { $gte: ?0} }")
    List<User> findUsersCreatedAfter(Date startDate);
}