package com.dispel4py.rest.controller;
import com.dispel4py.rest.model.PE;
import com.dispel4py.rest.model.Registry;
import com.dispel4py.rest.service.RegistryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/registry")
public class RegistryController {

    private RegistryService registryService;

    public RegistryController(RegistryService registryService) {
        this.registryService = registryService;
    }

    @GetMapping("/search/{search}/type/{type}")
    public List<Registry> search(@PathVariable(value="search") String search, @PathVariable(value="type") String type){
        return registryService.search(search,type);
    }
    @GetMapping("/all")
    public List<Registry> getAll(){
        return registryService.getAll();
    }

}
