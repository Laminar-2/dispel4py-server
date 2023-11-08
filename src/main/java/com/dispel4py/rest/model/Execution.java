package com.dispel4py.rest.model;

import java.io.Serializable;

public class Execution implements Serializable {

    Long workflowId;
    String workflowName;
    Workflow graph;
    String inputCode;
    String workflowCode;
    String resources;
    String imports;

    public Execution(Long workflowId, Workflow graph, String imports) {
        this.workflowId = workflowId;
        this.graph = graph;
        this.imports = imports;
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

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public String getImports() {
        return imports;
    }

    public void setImports(String imports) {
        this.imports = imports;
    }


    @Override
    public String toString() {
        return "Execution(" + "\n" + this.getWorkflowId() + "\n" + this.getInputCode() + this.getGraph() + "\n" + ")";
    }
}
