package com.aterehov.controller;

import com.aterehov.model.User;
import com.aterehov.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping
    public Mono<ResponseEntity<List<User>>> getUsers() {
        return Mono.just(ResponseEntity.ok(service.getAll()));
    }

    @PostMapping
    public Mono<ResponseEntity<?>> saveUser(@RequestBody User user) {
        service.save(user);
        return Mono.just(new ResponseEntity<>(HttpStatus.CREATED));
    }
}
