package com.example.musify.repository;

import com.example.musify.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByLogin(String login);
    boolean existsByLogin(String login);
    boolean existsByEmail(String email);
}

