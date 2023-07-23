package com.dispel4py.rest.model;

import javax.persistence.*;
import java.util.List;

/**
 * Model Class to represent Workflows
 */
@Entity
@Table(name = "workflows")
public class Workflow extends Registry {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    Integer workflowId;

    @Column
    String workflowName;

    @Column(columnDefinition = "varchar(10000)")
    String workflowCode;

    @Column(unique = true)
    String entryPoint;

    @Column
    String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "workflow_pe",
            joinColumns = @JoinColumn(name = "pe_id"),
            inverseJoinColumns = @JoinColumn(name = "workflow_id"))
    List<PE> PEs;

    @ManyToMany
    @JoinColumn(name = "userId", nullable = false)
    List<User> user;


    public Workflow(Integer id, String workflowName, String workflowCode, String entryPoint, String description, List<PE> PEs, List<User> user) {
        this.workflowId = id;
        this.workflowName = workflowName;
        this.workflowCode = workflowCode;
        this.entryPoint = entryPoint;
        this.description = description;
        this.PEs = PEs;
        this.user = user;
    }


    public Workflow() {

    }

    public Integer getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Integer workflowId) {
        this.workflowId = workflowId;
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

    public String getEntryPoint() {
        return entryPoint;
    }

    public void setEntryPoint(String entryPoint) {
        this.entryPoint = entryPoint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PE> getPEs() {
        return PEs;
    }

    public void setPEs(List<PE> PEs) {
        this.PEs = PEs;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Workflow(" + this.getWorkflowId() + "\n" + this.getWorkflowName()
                + "\n" + this.getEntryPoint() + "\n" + getDescription() + ")";
    }

}
