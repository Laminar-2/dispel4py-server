package com.dispel4py.rest.controller;

import com.dispel4py.rest.error.EntityExistsException;
import com.dispel4py.rest.error.EntityNotFoundException;
import com.dispel4py.rest.model.Workflow;
import com.dispel4py.rest.service.WorkflowService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path = "/registry/{user}/workflow")
public class WorkflowController {

    private final WorkflowService workflowService;

    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Workflow register(@RequestBody Workflow workflow, @PathVariable String user) throws EntityExistsException {
        return (workflowService.registerWorkflow(workflow, user));
    }

    @PutMapping("/{workflowId}/pe/{peId}")
    public Workflow assignPEtoWorkflow(@PathVariable Long peId, @PathVariable Long workflowId, @PathVariable String user) {
        return workflowService.assignPEtoWorkflow(peId, workflowId, user);
    }

    @GetMapping("/all")
    public List<Workflow> getAllWorkflows(@PathVariable String user) {
        return workflowService.getAllWorkflows(user);
    }

    @GetMapping("/id/{id}")
    public Workflow getWorkflowById(@PathVariable(value = "id") Long id, @PathVariable String user) throws EntityNotFoundException {
        return workflowService.getWorkflowByID(id, user);
    }

    @GetMapping("/name/{name}")
    public Workflow getWorkflowByName(@PathVariable(value = "name") String name, @PathVariable String user) throws EntityNotFoundException {
        return workflowService.getWorkflowByName(name, user);
    }

    @DeleteMapping("remove/id/{id}")
    public int removeWorkflowById(@PathVariable(value = "id") Long id, @PathVariable String user) {
        return workflowService.removeWorkflowByID(id, user);
    }

    @DeleteMapping("remove/name/{name}")
    public int removeWorkflowByName(@PathVariable(value = "name") String name, @PathVariable String user) {
        return workflowService.removeWorkflowByName(name, user);
    }

    @GetMapping("/pes/id/{id}")
    public Collection getPEsByWorkflow(@PathVariable(value = "id") Long id, @PathVariable String user) {
        return workflowService.getPEsByWorkflow(id, user);
    }


    @GetMapping("/pes/name/{name}")
    public Collection getPEsByWorkflow(@PathVariable(value = "name") String name, @PathVariable String user) {
        return workflowService.getPEsByWorkflow(name, user);
    }


}
