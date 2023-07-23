package com.dispel4py.rest.service;

import com.dispel4py.rest.model.PE;

import java.util.List;

/**
 * Interface for PEs
 */
public interface PEService {

    PE getPEByName(String peName, String user);

    PE getPEbyID(Long id, String user);

    PE registerPE(PE pe, String user);

    List<PE> getAllPEs(String user);

    int removePEbyID(Long id, String user);

    int removePEByName(String peName, String user);


}
