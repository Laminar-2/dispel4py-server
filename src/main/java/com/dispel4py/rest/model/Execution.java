package com.dispel4py.rest.model;

import java.io.Serializable;
import java.util.List;

public class Execution implements Serializable {

    Long workflowId;
    String workflowName;
    Workflow graph;
    String inputCode;
    Integer process;
    String workflowCode;
    List<String> resources;
    String imports;
    String user;

    public Execution(Long workflowId, Workflow graph, Integer process, List<String> resources, String imports) {
        this.workflowId = workflowId;
        this.graph = graph;
        this.process = process;
        this.imports = imports;
        this.resources = resources;
    }

    public Long getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
    }

    public Workflow getGraph() {
        return graph;
    }

    public void setGraph(Workflow graph) {
        this.graph = graph;
    }

    public void setProcess(Integer process) {
        this.process = process;
    }

    public Integer getProcess() {
        return process;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    public String getWorkflowCode() {
        return workflowCode;
    }

    public void setWorkflowCode(String workflowCode) {
        this.workflowCode = workflowCode;
    }

    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    public String getImports() {
        return imports;
    }

    public void setImports(String imports) {
        this.imports = imports;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Execution(" + "\n" + this.getWorkflowId() + "\n" + this.getInputCode() + this.getGraph() + "\n" + ")";
    }
}
