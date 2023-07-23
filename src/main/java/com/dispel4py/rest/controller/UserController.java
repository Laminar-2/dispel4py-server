package com.dispel4py.rest.controller;

import com.dispel4py.rest.model.User;
import com.dispel4py.rest.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User register(@RequestBody User user) throws Exception {
        return (userService.register(user));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@RequestBody User user) throws Exception {
        return (userService.login(user));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
