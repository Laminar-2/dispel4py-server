package com.dispel4py.rest.service;

import com.dispel4py.rest.model.Execution;
import com.dispel4py.rest.model.Workflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ExecutionServiceImpl implements ExecutionService {
    WorkflowService workflowService;

    @Autowired
    public ExecutionServiceImpl(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @Override
    public String runWorkflow(Execution e) {

        Long workflowId = e.getWorkflowId();
        String workflowName = e.getWorkflowName();
        Workflow wf;

        if(!(workflowId == null)){
            wf = workflowService.getWorkflowByID(workflowId);
            e.setGraph(wf);
        }else if(!(workflowName == null)){
            wf = workflowService.getWorkflowByName(workflowName);
            e.setGraph(wf);
        }

        System.out.println(e);

        WebClient webClient = WebClient.create("http://localhost:5000");

        String result = webClient.post()
                .uri("/run")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(e), Execution.class)
                .retrieve()
                .bodyToMono(String.class).block();

        System.out.println(result);

        return result;

    }
}
