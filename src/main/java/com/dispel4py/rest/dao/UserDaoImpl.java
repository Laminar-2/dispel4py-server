package com.dispel4py.rest.dao;

import com.dispel4py.rest.error.EntityExistsException;
import com.dispel4py.rest.error.EntityNotFoundException;
import com.dispel4py.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public User register(User user) {

        try {

            User check = getUserByName(user.getUserName());

            if (check != null) {
                throw new EntityExistsException(User.class, "userName", user.getUserName());
            }

        } catch (EntityNotFoundException ex) {
            User dbUser = entityManager.merge(user);
            return dbUser;

        }

        return null;

    }

    @Override
    public User getUserByName(String userName) {

        try {
            User user = (User) entityManager.createQuery("SELECT u FROM User u WHERE u.userName=:userName")
                    .setParameter("userName", userName).getSingleResult();

            return user;

        } catch (NoResultException ex) {
            throw new EntityNotFoundException(User.class, "userName", userName);
        }

    }

    @Override
    public List getAllUsers() {
        return entityManager.createQuery("Select t from User t")
                .getResultList();
    }

}
