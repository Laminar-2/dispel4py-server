package com.dispel4py.rest.controller;

import com.dispel4py.rest.error.EntityNotFoundException;
import com.dispel4py.rest.model.Execution;
import com.dispel4py.rest.service.ExecutionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import org.springframework.http.HttpStatus;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
@RequestMapping(path = "/execution/{user}")
public class ExecutionController {
    private final ExecutionService execService;

    public ExecutionController(ExecutionService execService) {
        this.execService = execService;
    }

    /*@RequestMapping(value = "/run", method = RequestMethod.POST)
    public String run(@RequestBody Execution e, @PathVariable String user) throws EntityNotFoundException {
        return execService.runWorkflow(e, user);
    }*/

    @RequestMapping(value = "/run", method = RequestMethod.POST, produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<String> run(@RequestBody Execution e, @PathVariable String user) throws EntityNotFoundException {
        /*
         * Based on code by micobg at https://stackoverflow.com/q/58668900 
         */
        Flux<String> fluxResponse = execService.runWorkflow(e, user);
        return fluxResponse;
    }
}
