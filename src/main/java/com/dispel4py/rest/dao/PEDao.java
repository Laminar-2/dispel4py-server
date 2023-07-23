package com.dispel4py.rest.dao;

import com.dispel4py.rest.model.PE;
import com.dispel4py.rest.model.User;

import java.util.List;

/**
 * Interface for interacting with DB
 */
public interface PEDao {

    PE registerPE(PE pe, User owner);

    PE getPEByName(String peName, String user);

    PE getPEbyId(Long id, String user);

    int removePEbyID(Long id, User owner);

    int removePEbyName(String peName, User owner);

    List<PE> searchPE(String search, String user);

    List<PE> getAllPEs(String user);

    PE persist(PE pe);
}
