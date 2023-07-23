package com.dispel4py.rest.controller;

import com.dispel4py.rest.error.EntityNotFoundException;
import com.dispel4py.rest.model.Execution;
import com.dispel4py.rest.service.ExecutionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/execution/{user}")
public class ExecutionController {
    private final ExecutionService execService;

    public ExecutionController(ExecutionService execService) {
        this.execService = execService;
    }

    @RequestMapping(value = "/run", method = RequestMethod.POST)
    public String run(@RequestBody Execution e, @PathVariable String user) throws EntityNotFoundException {
        return execService.runWorkflow(e, user);
    }
}
