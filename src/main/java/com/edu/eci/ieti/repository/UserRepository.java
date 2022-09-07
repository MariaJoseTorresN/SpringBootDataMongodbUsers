package com.edu.eci.ieti.repository;

import com.edu.eci.ieti.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {}