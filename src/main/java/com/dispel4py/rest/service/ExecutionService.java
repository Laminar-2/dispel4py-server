package com.dispel4py.rest.service;

import com.dispel4py.rest.model.Execution;
import reactor.core.publisher.Flux;

public interface ExecutionService {

    Flux<String> runWorkflow(Execution e, String user);
}
