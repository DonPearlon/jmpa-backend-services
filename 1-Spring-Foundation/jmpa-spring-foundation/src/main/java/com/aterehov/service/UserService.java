package com.aterehov.service;

import com.aterehov.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    List<User> getAll();
}
