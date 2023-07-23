package com.dispel4py.rest.service;

import com.dispel4py.rest.dao.RegistryDao;
import com.dispel4py.rest.model.Registry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RegistryServiceImpl implements RegistryService {

    RegistryDao registryDao;

    @Autowired
    public RegistryServiceImpl(RegistryDao registryDao) {
        this.registryDao = registryDao;
    }


    @Override
    @Transactional
    public List<Registry> search(String search, String type, String user) {
        return registryDao.search(search, type, user);
    }

    @Override
    public List<Registry> getAll(String user) {
        return registryDao.getAll(user);
    }
}
