package com.aterehov.service.impl;

import com.aterehov.model.User;
import com.aterehov.repository.UserRepository;
import com.aterehov.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public User getById(int id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }
}
