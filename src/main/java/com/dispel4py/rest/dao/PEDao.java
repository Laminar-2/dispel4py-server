package com.dispel4py.rest.dao;

import com.dispel4py.rest.model.PE;
import com.dispel4py.rest.model.Registry;

import java.util.List;

/**
 * Interface for interacting with DB
 */
public interface PEDao {

    PE registerPE(PE pe);
    PE getPEByName(String peName);
    PE getPEbyId(Long id);
    int removePEbyID(Long id);
    int removePEbyName(String peName);
    List<PE> searchPE(String search);
    List<PE> getAllPEs();

}
