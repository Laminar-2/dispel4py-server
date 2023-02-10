package com.dispel4py.rest.dao;
import com.dispel4py.rest.model.Registry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class RegistryDaoImpl implements  RegistryDao{

    PEDao peDao;
    WorkflowDao workflowDao;

    @Autowired
    public RegistryDaoImpl(PEDao peDao, WorkflowDao workflowDao) {
        this.peDao = peDao;
        this.workflowDao = workflowDao;
    }

    @Override
    public List<Registry> search(String search, String type) {

        ArrayList<Registry> results = new ArrayList<>();
        type = type.toLowerCase();

        if(type.equals("both")){

            results.addAll(peDao.searchPE(search));
            results.addAll(workflowDao.searchWorkflow(search));

            return results;

        }else if (type.equals("pe")){

            results.addAll(peDao.searchPE(search));
            return results;

        }else if (type.equals("workflow")){

            results.addAll(workflowDao.searchWorkflow(search));
            return results;

        }

        return null;//todo: some exception
    }

    @Override
    public List<Registry> getAll() {

        ArrayList<Registry> results = new ArrayList<>();

        results.addAll(peDao.getAllPEs());
        results.addAll(workflowDao.getAllWorkflows());

        return results;

    }
}
