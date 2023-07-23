package com.dispel4py.rest.dao;

import com.dispel4py.rest.model.Registry;

import java.util.List;

public interface RegistryDao {

    List<Registry> search(String search, String type, String user);

    List<Registry> getAll(String user);
}
