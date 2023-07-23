package com.dispel4py.rest.dao;

import com.dispel4py.rest.error.EntityExistsException;
import com.dispel4py.rest.error.EntityNotFoundException;
import com.dispel4py.rest.error.UnauthorizedException;
import com.dispel4py.rest.model.User;
import com.dispel4py.rest.model.Workflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class WorkflowDaoImpl implements WorkflowDao {

    private final EntityManager entityManager;

    @Autowired
    public WorkflowDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Workflow registerWorkflow(Workflow workflow, User owner) {

        Workflow check;

        try {

            check = getWorkflowByName(workflow.getEntryPoint(), owner.getUserName());

            if (check != null) {

                throw new EntityExistsException(Workflow.class, "entryPoint", workflow.getEntryPoint(), "id", Integer.toString(check.getWorkflowId()));
            }

        } catch (EntityNotFoundException ex) {

            ArrayList<User> users = new ArrayList<>();
            users.add(owner);
            workflow.setUser(users);

            Workflow dbWorkflow = entityManager.merge(workflow);
            return dbWorkflow;

        } catch (UnauthorizedException ex) {

            //add user to PE
            Workflow updateWf = (Workflow) entityManager.createQuery("SELECT u FROM Workflow u WHERE u.entryPoint=:entryPoint")
                    .setParameter("entryPoint", workflow.getEntryPoint()).getSingleResult();

            updateWf.getUser().add(owner);
            persist(updateWf);

            return updateWf;

        }

        return workflow;

    }

    @Override
    public Workflow getWorkflowByID(Long id, String user) {

        Workflow wf = entityManager.find(Workflow.class, id.intValue());

        if (wf == null) {
            throw new EntityNotFoundException(Workflow.class, "id", id.toString());
        }

        List<String> namesList = wf.getUser().stream()
                .map(User::getUserName)
                .collect(Collectors.toList());

        if (!namesList.contains(user)) {
            throw new UnauthorizedException(User.class, "userId", user, "workflowId", id.toString());
        }

        return wf;

    }

    @Override
    public Workflow persist(Workflow wf) {
        entityManager.persist(wf);
        return wf;
    }

    @Override
    public List<Workflow> getAllWorkflows(String user) {
        return entityManager.createQuery("Select t from Workflow t JOIN t.user r WHERE r.userName=:userId")
                .setParameter("userId", user)
                .getResultList();
    }

    @Override
    public List<Workflow> searchWorkflow(String search, String user) {
        TypedQuery<Workflow> typedQuery
                = entityManager.createQuery("SELECT u FROM Workflow u JOIN u.user r WHERE r.userName=:userId and lower(u.entryPoint) LIKE CONCAT('%',lower(:search),'%') OR u.description LIKE CONCAT('%',lower(:search),'%')", Workflow.class);
        typedQuery.setParameter("search", search).setParameter("userId", user);
        return typedQuery.getResultList();
    }

    @Override
    public Workflow getWorkflowByName(String workflowName, String user) {

        try {

            Workflow wf = entityManager.createQuery("SELECT u FROM Workflow u WHERE u.entryPoint=:workflowName", Workflow.class)
                    .setParameter("workflowName", workflowName).getSingleResult();

            for (User u : wf.getUser()) {
                if (u.getUserName().equals(user)) {
                    return wf;
                }
            }

            throw new UnauthorizedException(User.class, "userId", user, "entryPoint", workflowName);


        } catch (NoResultException ex) {
            throw new EntityNotFoundException(Workflow.class, "entryPoint", workflowName);

        }

    }

    @Override
    public int removeWorkflowByName(String workflowName, User owner) {

        Workflow workflowToRemove = getWorkflowByName(workflowName, owner.getUserName());

        if (workflowToRemove.getUser().size() > 1) {

            workflowToRemove.getUser().remove(owner);
            persist(workflowToRemove);

        } else {
            entityManager.remove(workflowToRemove);
        }


        return 1;
    }

    @Override
    public int removeWorkflowByID(Long id, User owner) {

        Workflow workflowToRemove = getWorkflowByID(id, owner.getUserName());

        if (workflowToRemove.getUser().size() > 1) {

            workflowToRemove.getUser().remove(owner);
            persist(workflowToRemove);

        } else {
            entityManager.remove(workflowToRemove);
        }


        return 1;
    }

    @Override
    public Collection getPEsByWorkflow(Long id, String user) {
        try {

            TypedQuery<Collection> typedQuery
                    = entityManager.createQuery("SELECT u.PEs FROM Workflow u JOIN u.user r WHERE r.userName=:userId and u.workflowId=:id", Collection.class);
            typedQuery.setParameter("id", id.intValue()).setParameter("userId", user);

            return typedQuery.getResultList();

        } catch (NoResultException ex) {
            throw new EntityNotFoundException(Workflow.class, "id", Integer.toString(id.intValue()));
        }

    }

    @Override
    public Collection getPEsByWorkflow(String workflowName, String user) {

        try {

            TypedQuery<Collection> typedQuery
                    = entityManager.createQuery("SELECT u.PEs FROM Workflow u JOIN u.user r WHERE r.userName=:userId and u.entryPoint=:workflowName", Collection.class);
            typedQuery.setParameter("workflowName", workflowName).setParameter("userId", user);

            return typedQuery.getResultList();

        } catch (NoResultException ex) {
            throw new EntityNotFoundException(Workflow.class, "workflowName", workflowName);
        }

    }
}
