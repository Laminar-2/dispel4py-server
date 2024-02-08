package com.dispel4py.rest.service;

import com.dispel4py.rest.model.Execution;
import com.dispel4py.rest.model.Response;
import reactor.core.publisher.Flux;

public interface ExecutionService {

    Flux<Response> runWorkflow(Execution e, String user);
}
