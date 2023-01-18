package com.dispel4py.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Model Class to represent Processing Elements (PEs)
 */
@Entity
@Table(name = "processing_elements")
public class PE extends Registry{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    Integer peId;
    @Column(unique = true)
    String peName;
    @Column(columnDefinition = "varchar(100000)")
    String peCode;
    @Column
    String description;
    @JsonIgnore
    @ManyToMany(mappedBy = "PEs")
    private List<Workflow> workflows;

    public PE(Integer id, String PEName, String PECode,
              String description) {

        this.peId = id;
        this.peName = PEName;
        this.peCode = PECode;
        this.description = description;

    }

    public PE() {

    }

    public Integer getPeId() {
        return peId;
    }

    public void setPeId(Integer peId) {
        this.peId = peId;
    }

    public String getPeName() {
        return peName;
    }

    public void setPeName(String PEName) {
        this.peName = PEName;
    }

    public String getpeCode() {
        return peCode;
    }

    public void setPeCode(String PECode) {
        this.peCode = PECode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Workflow> getWorkflows() {
        return workflows;
    }

    public void setWorkflows(List<Workflow> workflows) {
        this.workflows = workflows;
    }


}

