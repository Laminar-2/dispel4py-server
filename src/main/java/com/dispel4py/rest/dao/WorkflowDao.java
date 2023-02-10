package com.dispel4py.rest.dao;

import com.dispel4py.rest.model.PE;
import com.dispel4py.rest.model.Workflow;

import java.util.Collection;
import java.util.List;

/**
 * Interface for interacting with DB
 */
public interface WorkflowDao {

    Workflow registerWorkflow(Workflow workflow);

    Workflow getWorkflowByID(Long id);

    Workflow persist(Workflow workflow);

    List<Workflow> getAllWorkflows();

    List<Workflow> searchWorkflow(String search);


    Workflow getWorkflowByName(String workflowName);

    int removeWorkflowByName(String workflowName);

    int removeWorkflowByID(Long id);

    Collection getPEsByWorkflow(Long id);

    Collection getPEsByWorkflow(String workflowName);
}
