package com.dispel4py.rest.service;

import com.dispel4py.rest.dao.PEDao;
import com.dispel4py.rest.dao.UserDao;
import com.dispel4py.rest.model.PE;
import com.dispel4py.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


/**
 * Contains service logic for PE registration and retrieval
 */
@Service
public class PEServiceImpl implements PEService {

    PEDao PEDao;

    UserDao userDao;

    @Autowired
    public PEServiceImpl(PEDao PEDao, UserDao userDao) {
        this.PEDao = PEDao;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public PE registerPE(PE pe, String user) {

        User owner = userDao.getUserByName(user);

        return PEDao.registerPE(pe, owner);
    }

    @Override
    @Transactional
    public PE getPEByName(String peName, String user) {
        return PEDao.getPEByName(peName, user);
    }

    @Override
    @Transactional
    public PE getPEbyID(Long id, String user) {
        return PEDao.getPEbyId(id, user);
    }

    @Override
    @Transactional
    public List<PE> getAllPEs(String user) {
        return PEDao.getAllPEs(user);
    }

    @Override
    @Transactional
    public int removePEbyID(Long id, String user) {

        User owner = userDao.getUserByName(user);

        return PEDao.removePEbyID(id, owner);
    }

    @Override
    @Transactional
    public int removePEByName(String peName, String user) {

        User owner = userDao.getUserByName(user);

        return PEDao.removePEbyName(peName, owner);
    }

}
