package com.dispel4py.rest.service;

import com.dispel4py.rest.model.User;

import java.util.List;

public interface UserService {

    User register(User user) throws Exception;

    User login(User user) throws Exception;

    List<User> getAllUsers();

}
