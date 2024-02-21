package com.dispel4py.rest.controller;

import com.dispel4py.rest.error.EntityNotFoundException;
import com.dispel4py.rest.model.Execution;
import com.dispel4py.rest.service.ExecutionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import org.springframework.http.HttpStatus;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping(path = "/execution/{user}")
public class ExecutionController {
    private final ExecutionService execService;

    public ExecutionController(ExecutionService execService) {
        this.execService = execService;
    }

    @RequestMapping(value = "/resource", method = RequestMethod.PUT, consumes = MULTIPART_FORM_DATA_VALUE)
    public void resource(@RequestParam("files") MultipartFile[] files, @PathVariable String user) {
        execService.sendResources(files, user);
    }

    @RequestMapping(value = "/run", method = RequestMethod.POST, produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<String> run(@RequestBody Execution e, @PathVariable String user) throws EntityNotFoundException {
        /*
         * Based on code by micobg at https://stackoverflow.com/q/58668900 
         */
        Flux<String> fluxResponse = execService.runWorkflow(e, user);
        return fluxResponse;
    }
}
