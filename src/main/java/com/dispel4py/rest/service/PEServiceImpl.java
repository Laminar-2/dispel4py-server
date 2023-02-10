package com.dispel4py.rest.service;
import com.dispel4py.rest.dao.PEDao;
import com.dispel4py.rest.model.PE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;


/**
 * Contains service logic for PE registration and retrieval
 */
@Service
public class PEServiceImpl implements PEService{

    PEDao PEDao;
    @Autowired
    public PEServiceImpl(PEDao PEDao) {
        this.PEDao = PEDao;
    }

    @Override
    @Transactional
    public PE registerPE(PE pe) {
        return PEDao.registerPE(pe);
    }

    @Override
    @Transactional
    public PE getPEByName(String peName) {
        return PEDao.getPEByName(peName); //todo: handle multiple PEs
    }

    @Override
    @Transactional
    public PE getPEbyID(Long id) {
        return PEDao.getPEbyId(id);
    }

    @Override
    @Transactional
    public List<PE> getAllPEs() {
        return PEDao.getAllPEs();
    }

    @Override
    @Transactional
    public int removePEbyID(Long id) {
        return PEDao.removePEbyID(id);
    }

    @Override
    @Transactional
    public int removePEByName(String peName) {
        return PEDao.removePEbyName(peName);
    }

}
