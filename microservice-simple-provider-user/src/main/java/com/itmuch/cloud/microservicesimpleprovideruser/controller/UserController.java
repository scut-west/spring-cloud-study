package com.itmuch.cloud.microservicesimpleprovideruser.controller;

import com.itmuch.cloud.microservicesimpleprovideruser.dao.UserRepository;
import com.itmuch.cloud.microservicesimpleprovideruser.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User findById(@PathVariable long id) {
        User user = this.userRepository.getOne(id);
        return user;
    }
}
