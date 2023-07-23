package com.dispel4py.rest.dao;

import com.dispel4py.rest.model.Registry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RegistryDaoImpl implements RegistryDao {

    PEDao peDao;
    WorkflowDao workflowDao;

    @Autowired
    public RegistryDaoImpl(PEDao peDao, WorkflowDao workflowDao) {
        this.peDao = peDao;
        this.workflowDao = workflowDao;
    }

    @Override
    public List<Registry> search(String search, String type, String user) {

        ArrayList<Registry> results = new ArrayList<>();
        type = type.toLowerCase();

        if (type.equals("both")) {

            results.addAll(peDao.searchPE(search, user));
            results.addAll(workflowDao.searchWorkflow(search, user));

            return results;

        } else if (type.equals("pe")) {

            results.addAll(peDao.searchPE(search, user));
            return results;

        } else if (type.equals("workflow")) {

            results.addAll(workflowDao.searchWorkflow(search, user));
            return results;

        }

        return null;
    }

    @Override
    public List<Registry> getAll(String user) {

        ArrayList<Registry> results = new ArrayList<>();

        results.addAll(peDao.getAllPEs(user));
        results.addAll(workflowDao.getAllWorkflows(user));

        return results;

    }
}
