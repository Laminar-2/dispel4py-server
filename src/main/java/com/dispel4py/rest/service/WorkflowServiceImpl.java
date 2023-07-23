package com.dispel4py.rest.service;

import com.dispel4py.rest.dao.PEDao;
import com.dispel4py.rest.dao.UserDao;
import com.dispel4py.rest.dao.WorkflowDao;
import com.dispel4py.rest.model.PE;
import com.dispel4py.rest.model.User;
import com.dispel4py.rest.model.Workflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * Contains service logic for Workflow registration and retrieval
 */

@Service
public class WorkflowServiceImpl implements WorkflowService {

    WorkflowDao workflowDao;
    PEDao peDao;

    UserDao userDao;

    @Autowired
    public WorkflowServiceImpl(WorkflowDao workflowDao, PEDao peDao, UserDao userDao) {
        this.workflowDao = workflowDao;
        this.peDao = peDao;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public Workflow registerWorkflow(Workflow workflow, String user) {

        User owner = userDao.getUserByName(user);

        return workflowDao.registerWorkflow(workflow, owner);
    }

    @Override
    @Transactional
    public Workflow assignPEtoWorkflow(Long peId, Long workflowId, String user) {

        List<PE> PESet = null;
        Workflow workflow = workflowDao.getWorkflowByID(workflowId, user);
        PE pe = peDao.getPEbyId(peId, user);

        if (workflow.getPEs().contains(pe)) {
            return workflow;
        }

        PESet = workflow.getPEs();
        PESet.add(pe);
        workflow.setPEs(PESet);
        workflowDao.persist(workflow);

        return workflow;
    }

    @Override
    @Transactional
    public List<Workflow> getAllWorkflows(String user) {
        return workflowDao.getAllWorkflows(user);
    }

    @Override
    @Transactional
    public Workflow getWorkflowByID(Long workflowId, String user) {
        return workflowDao.getWorkflowByID(workflowId, user);
    }

    @Override
    @Transactional
    public Workflow getWorkflowByName(String name, String user) {
        return workflowDao.getWorkflowByName(name, user);
    }

    @Override
    @Transactional
    public int removeWorkflowByName(String name, String user) {

        User owner = userDao.getUserByName(user);

        return workflowDao.removeWorkflowByName(name, owner);
    }

    @Override
    @Transactional
    public int removeWorkflowByID(Long workflowId, String user) {

        User owner = userDao.getUserByName(user);

        return workflowDao.removeWorkflowByID(workflowId, owner);
    }

    @Override
    @Transactional
    public Collection getPEsByWorkflow(Long id, String user) {
        return workflowDao.getPEsByWorkflow(id, user);
    }

    @Override
    @Transactional
    public Collection getPEsByWorkflow(String name, String user) {
        return workflowDao.getPEsByWorkflow(name, user);
    }


}
