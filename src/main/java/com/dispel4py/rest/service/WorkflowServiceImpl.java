package com.dispel4py.rest.service;
import com.dispel4py.rest.dao.PEDao;
import com.dispel4py.rest.dao.WorkflowDao;
import com.dispel4py.rest.model.PE;
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
public class WorkflowServiceImpl implements WorkflowService{

    WorkflowDao workflowDao;
    PEDao peDao;

    @Autowired
    public WorkflowServiceImpl(WorkflowDao workflowDao, PEDao peDao) {
        this.workflowDao = workflowDao;
        this.peDao = peDao;
    }

    @Override
    @Transactional
    public Workflow registerWorkflow(Workflow workflow) {
        return workflowDao.registerWorkflow(workflow);
    }

    @Override
    @Transactional
    public Workflow assignPEtoWorkflow(Long peId, Long workflowId) {
        List<PE> PESet = null;

        Workflow workflow = workflowDao.getWorkflowByID(workflowId);
        PE pe = peDao.getPEbyId(peId);
        PESet = workflow.getPEs();
        PESet.add(pe);
        workflow.setPEs(PESet);
        workflowDao.persist(workflow);

        return  workflow;
    }

    @Override
    @Transactional
    public List<Workflow> getAllWorkflows() {
        return workflowDao.getAllWorkflows();
    }

    @Override
    @Transactional
    public Workflow getWorkflowByID(Long workflowId) {
        return workflowDao.getWorkflowByID(workflowId);
    }

    @Override
    @Transactional
    public Workflow getWorkflowByName(String name) {
        return workflowDao.getWorkflowByName(name);
    }

    @Override
    @Transactional
    public int removeWorkflowByName(String name) {
        return workflowDao.removeWorkflowByName(name);
    }

    @Override
    @Transactional
    public int removeWorkflowByID(Long workflowId) {
        return workflowDao.removeWorkflowByID(workflowId);
    }

    @Override
    @Transactional
    public Collection getPEsByWorkflow(Long id) {
        return workflowDao.getPEsByWorkflow(id);
    }

    @Override
    @Transactional
    public Collection getPEsByWorkflow(String name) {
        return workflowDao.getPEsByWorkflow(name);
    }


}
