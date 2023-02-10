package com.dispel4py.rest.dao;
import com.dispel4py.rest.error.EntityExistsException;
import com.dispel4py.rest.error.EntityNotFoundException;
import com.dispel4py.rest.model.PE;
import com.dispel4py.rest.model.Workflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;

@Repository
public class WorkflowDaoImpl implements WorkflowDao{

    private EntityManager entityManager;

    @Autowired
    public WorkflowDaoImpl(EntityManager entityManager) {this.entityManager = entityManager;}

    @Override
    public Workflow registerWorkflow(Workflow workflow) {

        try{

            Workflow check = getWorkflowByName(workflow.getEntryPoint());

            if(check != null){

                throw new EntityExistsException(Workflow.class,"entryPoint", workflow.getEntryPoint(),"id",Integer.toString(check.getWorkflowId()));
            }

        }catch(EntityNotFoundException ex){
            Workflow dbWorkflow = entityManager.merge(workflow);
            return dbWorkflow;
        }

        return workflow;

    }

    @Override
    public Workflow getWorkflowByID(Long id) {

        Workflow wf = entityManager.find(Workflow.class, id.intValue());
        if (wf == null) {
            throw new EntityNotFoundException(Workflow.class, "id", id.toString());
        }
        return wf;

    }

    @Override
    public Workflow persist(Workflow wf) {
        entityManager.persist(wf);
        return wf;
    }

    @Override
    public List<Workflow> getAllWorkflows() {
        return entityManager.createQuery("Select t from Workflow t").getResultList();
    }

    @Override
    public List<Workflow> searchWorkflow(String search) {
        TypedQuery<Workflow> typedQuery
                = entityManager.createQuery("SELECT u FROM Workflow u WHERE lower(u.workflowName) LIKE CONCAT('%',lower(:search),'%') OR u.description LIKE CONCAT('%',lower(:search),'%')", Workflow.class);
        typedQuery.setParameter("search", search);
        return typedQuery.getResultList();
    }

    @Override
    public Workflow getWorkflowByName(String workflowName) {

        try {
            TypedQuery<Workflow> typedQuery
                    = entityManager.createQuery("SELECT u FROM Workflow u WHERE u.entryPoint=:workflowName", Workflow.class);
            typedQuery.setParameter("workflowName", workflowName);
            return typedQuery.getSingleResult();
        }catch (NoResultException ex){
            throw new EntityNotFoundException(Workflow.class, "entryPoint", workflowName);
        }
    }

    @Override
    public int removeWorkflowByName(String workflowName) {
        return entityManager.createQuery("DELETE FROM Workflow u WHERE u.workflowName=:workflowName")
                .setParameter("workflowName", workflowName)
                .executeUpdate();
    }

    @Override
    public int removeWorkflowByID(Long id) {
        return entityManager.createQuery("DELETE FROM Workflow u WHERE u.workflowId=:workflowId")
                .setParameter("workflowId", id.intValue())
                .executeUpdate();
    }

    @Override
    public Collection getPEsByWorkflow(Long id) {
        try {

            TypedQuery<Collection> typedQuery
                    = entityManager.createQuery("SELECT u.PEs FROM Workflow u WHERE u.workflowId=:id", Collection.class);
            typedQuery.setParameter("id", id.intValue());

            return typedQuery.getResultList();

        }catch (NoResultException ex){
            throw new EntityNotFoundException(Workflow.class, "id", Integer.toString(id.intValue()));
        }

    }

    @Override
    public Collection getPEsByWorkflow(String workflowName) {

        try {

            TypedQuery<Collection> typedQuery
                    = entityManager.createQuery("SELECT u.PEs FROM Workflow u WHERE u.workflowName=:workflowName", Collection.class);
            typedQuery.setParameter("workflowName", workflowName);

            return typedQuery.getResultList();

        }catch (NoResultException ex){
            throw new EntityNotFoundException(Workflow.class, "workflowName", workflowName);
        }

    }
}
