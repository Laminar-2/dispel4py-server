package com.dispel4py.rest.controller;
import com.dispel4py.rest.error.EntityExistsException;
import com.dispel4py.rest.error.EntityNotFoundException;
import com.dispel4py.rest.model.PE;
import com.dispel4py.rest.model.Workflow;
import com.dispel4py.rest.service.WorkflowService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path="/registry/workflow")
public class WorkflowController {

    private WorkflowService workflowService;

    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Workflow register(@RequestBody Workflow workflow) throws EntityExistsException {
        return(workflowService.registerWorkflow(workflow));
    }

    @PutMapping("/{workflowId}/pe/{peId}")
    public Workflow assignPEtoWorkflow(@PathVariable Long peId, @PathVariable Long workflowId){
        return workflowService.assignPEtoWorkflow(peId,workflowId);
    }

    @GetMapping("/all")
    public List<Workflow> getAllWorkflows(){
        return workflowService.getAllWorkflows();
    }

    @GetMapping("/id/{id}")
    public Workflow getWorkflowById(@PathVariable(value="id") Long id) throws EntityNotFoundException {
        return workflowService.getWorkflowByID(id);
    }

    @GetMapping("/name/{name}")
    public Workflow getWorkflowByName(@PathVariable(value="name") String name) throws EntityNotFoundException{
        return workflowService.getWorkflowByName(name);
    }

    @GetMapping("remove/id/{id}")
    public int removeWorkflowById(@PathVariable(value="id") Long id){
        return workflowService.removeWorkflowByID(id);
    }

    @GetMapping("remove/name/{name}")
    public int removeWorkflowByName(@PathVariable(value="name") String name){
        return workflowService.removeWorkflowByName(name);
    }

    @GetMapping("/pes/id/{id}")
    public List<PE> getPEsByWorkflow(@PathVariable(value="id") Long id){
        return workflowService.getPEsByWorkflow(id);
    }


    @GetMapping("/pes/name/{id}")
    public List<PE> getPEsByWorkflow(@PathVariable(value="id") String name){
        return workflowService.getPEsByWorkflow(name);
    }


}
