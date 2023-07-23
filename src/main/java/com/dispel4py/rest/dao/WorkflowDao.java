package com.dispel4py.rest.dao;

import com.dispel4py.rest.model.User;
import com.dispel4py.rest.model.Workflow;

import java.util.Collection;
import java.util.List;

/**
 * Interface for interacting with DB
 */
public interface WorkflowDao {

    Workflow registerWorkflow(Workflow workflow, User owner);

    Workflow getWorkflowByID(Long id, String user);

    Workflow persist(Workflow workflow);

    List<Workflow> getAllWorkflows(String user);

    List<Workflow> searchWorkflow(String search, String user);


    Workflow getWorkflowByName(String workflowName, String user);

    int removeWorkflowByName(String workflowName, User owner);

    int removeWorkflowByID(Long id, User owner);

    Collection getPEsByWorkflow(Long id, String user);

    Collection getPEsByWorkflow(String workflowName, String user);
}
