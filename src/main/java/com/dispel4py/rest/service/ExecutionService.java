package com.dispel4py.rest.service;

import com.dispel4py.rest.model.Execution;
import reactor.core.publisher.Flux;
import org.springframework.web.multipart.MultipartFile;

public interface ExecutionService {

    void sendResources(MultipartFile[] files, String user);

    Flux<String> runWorkflow(Execution e, String user);
}
