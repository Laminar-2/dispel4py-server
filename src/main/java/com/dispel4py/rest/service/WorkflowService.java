package com.dispel4py.rest.service;

import com.dispel4py.rest.model.PE;
import com.dispel4py.rest.model.Workflow;

import java.util.List;

public interface WorkflowService {
    Workflow registerWorkflow(Workflow workflow);

    Workflow assignPEtoWorkflow(Long peId, Long workflowId);

    List<Workflow> getAllWorkflows();

    Workflow getWorkflowByID(Long workflowId);

    Workflow getWorkflowByName(String name);

    int removeWorkflowByName(String name);

    int removeWorkflowByID(Long workflowId);
    List<PE> getPEsByWorkflow(Long id);
    List<PE> getPEsByWorkflow(String name);
}
