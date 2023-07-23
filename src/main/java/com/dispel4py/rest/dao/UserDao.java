package com.dispel4py.rest.dao;

import com.dispel4py.rest.model.User;

import java.util.List;

public interface UserDao {

    User register(User user);

    User getUserByName(String userName);

    List<User> getAllUsers();

}
