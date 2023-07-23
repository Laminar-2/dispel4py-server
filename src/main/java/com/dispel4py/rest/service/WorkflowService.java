package com.dispel4py.rest.service;

import com.dispel4py.rest.model.Workflow;

import java.util.Collection;
import java.util.List;

public interface WorkflowService {
    Workflow registerWorkflow(Workflow workflow, String user);

    Workflow assignPEtoWorkflow(Long peId, Long workflowId, String user);

    List<Workflow> getAllWorkflows(String user);

    Workflow getWorkflowByID(Long workflowId, String user);

    Workflow getWorkflowByName(String name, String user);

    int removeWorkflowByName(String name, String user);

    int removeWorkflowByID(Long workflowId, String user);

    Collection getPEsByWorkflow(Long id, String user);

    Collection getPEsByWorkflow(String name, String user);
}
