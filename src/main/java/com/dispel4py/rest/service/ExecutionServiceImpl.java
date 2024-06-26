package com.dispel4py.rest.service;

import com.dispel4py.rest.model.Execution;
import com.dispel4py.rest.model.PE;
import com.dispel4py.rest.model.Workflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import org.springframework.core.env.Environment;
import reactor.core.publisher.Flux;
import org.springframework.web.reactive.function.BodyInserters;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;

@Service
public class ExecutionServiceImpl implements ExecutionService {
    WorkflowService workflowService;

    @Autowired
    private Environment env;

    @Autowired
    public ExecutionServiceImpl(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @Override
    public void sendResources(MultipartFile[] files, String user) {
        String url = env.getProperty("laminar.execution.url");
        WebClient webClient = WebClient.create(url);

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("user", user);
        for (MultipartFile file : files) {
            builder.part("files", file.getResource());
        }

        Mono<Void> result = webClient.put()
                .uri("/resource")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Flux<String> runWorkflow(Execution e, String user) {

        //e.imports will already be set from client for direct execution

        Long workflowId = e.getWorkflowId();
        String workflowName = e.getWorkflowName();
        Workflow wf;

        e.setUser(user);

        if (!(workflowId == null)) {
            wf = workflowService.getWorkflowByID(workflowId, user);
            e.setGraph(wf);

            //Set import string from joining pe imports
            String imports = "";
            for (PE pe : wf.getPEs()) {
                String s = pe.getPeImports();
                System.out.println("" + s);
                if (!s.equals("")) {
                    imports = imports + "," + s;
                }
            }
            e.setImports(imports);

        } else if (!(workflowName == null)) {
            wf = workflowService.getWorkflowByName(workflowName, user);
            e.setGraph(wf);
            String imports = "";
            for (PE pe : wf.getPEs()) {
                String s = pe.getPeImports();
                System.out.println("" + s);
                if (!s.equals("")) {
                    imports = imports + "," + s;
                }
            }
            e.setImports(imports);
        }

        System.out.println(e);

        //WebClient webClient = WebClient.create("https://executionengined4py.azurewebsites.net");
        String url = env.getProperty("laminar.execution.url");
        WebClient webClient = WebClient.create(url);

        Flux<String> result = webClient.post()
                .uri("/run")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(e), Execution.class)
                .retrieve()
                .bodyToFlux(String.class)
                .log();
                //.bodyToMono(String.class).block();

        System.out.println(result);
        result.subscribe(System.out::println);

        return result;

    }
}
