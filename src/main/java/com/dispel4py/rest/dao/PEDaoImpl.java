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
import java.util.List;

@Repository
public class PEDaoImpl implements PEDao{

    private EntityManager entityManager;

    @Autowired
    public PEDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public PE registerPE(PE pe) {

        try{
            if(getPEByName(pe.getPeName()) != null){
                throw new EntityExistsException(PE.class, "peName", pe.getPeName());
            }

        }catch(EntityNotFoundException ex){
            PE dbPe = entityManager.merge(pe);
            return dbPe;
        }

        return pe;
    }

    @Override
    public PE getPEByName(String peName) {

        try{
            PE pe = (PE) entityManager.createQuery("SELECT u FROM PE u WHERE u.peName=:peName")
                    .setParameter("peName", peName)
                    .getSingleResult();
            return pe;
        }catch(NoResultException ex){
            throw new EntityNotFoundException(PE.class, "peName", peName);
        }
    }

    @Override
    public PE getPEbyId(Long id) {
        PE pe = entityManager.find(PE.class, id.intValue());
        if (pe == null) {
            throw new EntityNotFoundException(PE.class, "id", id.toString());
        }
        return pe;
    }

    @Override
    public int removePEbyID(Long id) {
        return entityManager.createQuery("DELETE FROM PE u WHERE u.peId=:peId")
                .setParameter("peId", id.intValue())
                .executeUpdate();
    }

    @Override
    public int removePEbyName(String peName) {

        return entityManager.createQuery("DELETE FROM PE u WHERE u.peName=:peName")
                .setParameter("peName", peName)
                .executeUpdate();
    }

    @Override
    public List<PE> searchPE(String search) {
        TypedQuery<PE> typedQuery
                = entityManager.createQuery("SELECT u FROM PE u WHERE lower(u.peName) LIKE CONCAT('%',lower(:search),'%') OR u.description LIKE CONCAT('%',lower(:search),'%')", PE.class);
        typedQuery.setParameter("search", search);
        return typedQuery.getResultList();
    }

    @Override
    public List<PE> getAllPEs() {
        return entityManager.createQuery("Select t from PE t").getResultList();
    }

}
