package com.aterehov.repository;

import com.aterehov.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Override
    List<User> findAll();
}
