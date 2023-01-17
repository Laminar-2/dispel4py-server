package com.dispel4py.rest.service;
import com.dispel4py.rest.model.PE;

import java.util.List;

/**
 * Interface for PEs
 */
public interface PEService {

    PE getPEByName(String peName);
    PE getPEbyID(Long id);
    PE registerPE(PE pe);

    List<PE> getAllPEs();

    int removePEbyID(Long id);
    int removePEByName(String peName);

    //getPEsbyWorkflow();




}
