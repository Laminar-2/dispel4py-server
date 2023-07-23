package com.dispel4py.rest.controller;

import com.dispel4py.rest.model.Registry;
import com.dispel4py.rest.service.RegistryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/registry/{user}")
public class RegistryController {

    private final RegistryService registryService;

    public RegistryController(RegistryService registryService) {
        this.registryService = registryService;
    }

    @GetMapping("/search/{search}/type/{type}")
    public List<Registry> search(@PathVariable(value = "search") String search, @PathVariable(value = "type") String type, @PathVariable String user) {
        return registryService.search(search, type, user);
    }

    @GetMapping("/all")
    public List<Registry> getAll(@PathVariable String user) {
        return registryService.getAll(user);
    }

}
