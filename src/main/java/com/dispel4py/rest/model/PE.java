package com.dispel4py.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import org.hibernate.type.descriptor.sql.LongVarcharTypeDescriptor;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Model Class to represent Processing Elements (PEs)
 */
@Entity
@Table(name = "processing_elements")
public class PE extends Registry {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    Integer peId;
    @Column(unique = true)
    String peName;
    @Column(length = 5000)
    String peCode;
    @Column
    String description;
    @Column
    String peImports;
    @Column(length = 20000)
    byte[] codeEmbedding;
    @Column(length = 20000)
    LongVarcharTypeDescriptor descEmbedding;
    @JsonIgnore
    @ManyToMany(mappedBy = "PEs")
    private List<Workflow> workflows;

    @ManyToMany
    @JoinColumn(name = "userId", nullable = false)
    List<User> user;

    public PE(Integer id, String PEName, String PECode,
              String description, String peImports,
              String codeEmbeddings, LongVarcharTypeDescriptor descEmbeddings,List<User> user) {

        this.peId = id;
        this.peName = PEName;
        this.peCode = PECode;
        this.description = description;
        this.peImports = peImports;
        this.user = user;
        try {
            this.codeEmbedding = codeEmbeddings.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            System.err.println("Unsupported encoding type 'utf-8'");
            System.exit(1);
        }
        this.descEmbedding = descEmbeddings;

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

    public String getPeImports() {
        return peImports;
    }

    public void setPeImports(String peImports) {
        this.peImports = peImports;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public String getCodeEmbedding() {
        try {
            return new String(codeEmbedding, "utf-8");
        } catch (UnsupportedEncodingException e) {
            System.err.println("Unsupported encoding type 'utf-8'");
            System.exit(1);
        }
        return "error";
    }

    public void setCodeEmbedding(String codeEmbeddings) {
        try {
            this.codeEmbedding = codeEmbeddings.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            System.err.println("Unsupported encoding type 'utf-8'");
            System.exit(1);
        }
    }

    public LongVarcharTypeDescriptor getDescEmbedding() {
        return descEmbedding;
    }

    public void setDescEmbedding(LongVarcharTypeDescriptor descEmbeddings) {
        this.descEmbedding = descEmbeddings;
    }

    @Override
    public String toString() {
        return "PE(" + this.getPeId() + "\n" + this.getPeName()
                + ")";
    }


}

