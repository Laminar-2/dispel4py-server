package com.dispel4py.rest.service;

import com.dispel4py.rest.model.Registry;

import java.util.List;

/**
 * General interface for registry
 */
public interface RegistryService {

    List<Registry> search(String search, String type, String user);

    List<Registry> getAll(String user);
}
