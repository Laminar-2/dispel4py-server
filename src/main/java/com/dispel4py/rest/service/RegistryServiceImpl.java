package com.dispel4py.rest.service;

import com.dispel4py.rest.dao.RegistryDao;
import com.dispel4py.rest.model.Registry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RegistryServiceImpl implements RegistryService{

    RegistryDao registryDao;

    @Autowired
    public RegistryServiceImpl(RegistryDao registryDao) {
        this.registryDao = registryDao;
    }


    @Override
    @Transactional
    public List<Registry> search(String search, String type) {
        return registryDao.search(search,type);
    }

    @Override
    public List<Registry> getAll() {
        return registryDao.getAll();
    }
}
