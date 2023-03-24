package com.dispel4py.rest.service;

import com.dispel4py.rest.model.Execution;

public interface ExecutionService {

    String runWorkflow(Execution e, String user);
}
