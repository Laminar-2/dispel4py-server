package com.dispel4py.rest.dao;

import com.dispel4py.rest.error.EntityExistsException;
import com.dispel4py.rest.error.EntityNotFoundException;
import com.dispel4py.rest.error.UnauthorizedException;
import com.dispel4py.rest.model.PE;
import com.dispel4py.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PEDaoImpl implements PEDao {

    private final EntityManager entityManager;

    @Autowired
    public PEDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public PE registerPE(PE pe, User owner) {

        PE check;

        try {

            check = getPEByName(pe.getPeName(), owner.getUserName());

            if (check != null) {
                throw new EntityExistsException(PE.class, "peName", pe.getPeName(), "userName", owner.getUserName());
            }

        } catch (EntityNotFoundException ex) {
            //new PE being added with user
            ArrayList<User> users = new ArrayList<>();
            users.add(owner);
            pe.setUser(users);

            PE dbPe = entityManager.merge(pe);
            return dbPe;

        } catch (UnauthorizedException ex) {

            //add user to PE
            PE updatePe = (PE) entityManager.createQuery("SELECT u FROM PE u WHERE u.peName=:peName")
                    .setParameter("peName", pe.getPeName()).getSingleResult();

            updatePe.getUser().add(owner);
            persist(updatePe);

            return updatePe;

        }

        return pe;
    }

    @Override
    public PE getPEByName(String peName, String user) {

        try {

            PE pe = (PE) entityManager.createQuery("SELECT u FROM PE u WHERE u.peName=:peName")
                    .setParameter("peName", peName).getSingleResult();

            for (User u : pe.getUser()) {
                if (u.getUserName().equals(user)) {
                    return pe;
                }
            }

            //PE exists but belongs to other users
            throw new UnauthorizedException(User.class, "userId", user, "peName", peName);

        } catch (NoResultException ex) {
            throw new EntityNotFoundException(PE.class, "peName", peName);
        }

    }

    @Override
    public PE getPEbyId(Long id, String user) {


        PE pe = entityManager.find(PE.class, id.intValue());

        if (pe == null) {
            throw new EntityNotFoundException(PE.class, "id", id.toString());
        }

        List<String> namesList = pe.getUser().stream()
                .map(User::getUserName)
                .collect(Collectors.toList());

        if (!namesList.contains(user)) {
            throw new UnauthorizedException(User.class, "userId", user, "peId", id.toString());
        }

        return pe;

    }

    @Override
    public int removePEbyID(Long id, User owner) {

        PE peToRemove = getPEbyId(id, owner.getUserName());

        if (peToRemove.getUser().size() > 1) {

            peToRemove.getUser().remove(owner);
            persist(peToRemove);

        } else {
            entityManager.remove(peToRemove);
        }

        return 1;
    }

    @Override
    public int removePEbyName(String peName, User owner) {

        PE peToRemove = getPEByName(peName, owner.getUserName());

        if (peToRemove.getUser().size() > 1) {

            peToRemove.getUser().remove(owner);
            persist(peToRemove);

        } else {
            entityManager.remove(peToRemove);
        }

        return 1;
    }

    @Override
    public List<PE> searchPE(String search, String user) {
        TypedQuery<PE> typedQuery
                = entityManager.createQuery("SELECT u FROM PE u JOIN u.user r WHERE r.userName=:userId and lower(u.peName) LIKE CONCAT('%',lower(:search),'%') OR u.description LIKE CONCAT('%',lower(:search),'%')", PE.class);
        typedQuery.setParameter("search", search)
                .setParameter("userId", user);
        return typedQuery.getResultList();
    }

    @Override
    public List<PE> getAllPEs(String user) {
        return entityManager.createQuery("Select t from PE t JOIN t.user r WHERE r.userName=:userId")
                .setParameter("userId", user)
                .getResultList();
    }

    @Override
    public PE persist(PE pe) {
        entityManager.persist(pe);
        return pe;
    }

}
