package com.dispel4py.rest.service;

import com.dispel4py.rest.dao.UserDao;
import com.dispel4py.rest.error.AuthenticationException;
import com.dispel4py.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public User register(User user) {


        return userDao.register(user);
    }

    @Override
    @Transactional
    public User login(User user) {

        User check = userDao.getUserByName(user.getUserName());
        String password = check.getPassword();

        if (password.equals(user.getPassword())) {
            return check;
        } else {
            throw new AuthenticationException(User.class, "password", user.getPassword());
        }

    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }


}
