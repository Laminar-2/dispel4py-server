package com.dispel4py.rest.controller;

import com.dispel4py.rest.error.EntityExistsException;
import com.dispel4py.rest.error.EntityNotFoundException;
import com.dispel4py.rest.model.PE;
import com.dispel4py.rest.service.PEService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/registry/{user}/pe/")
public class PEController {

    private final PEService peService;

    public PEController(PEService peService) {
        this.peService = peService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public PE register(@RequestBody PE pe, @PathVariable String user) throws EntityExistsException {
        return peService.registerPE(pe, user);
    }

    @GetMapping("/id/{id}")
    public PE getPEbyId(@PathVariable(value = "id") Long id, @PathVariable String user) throws EntityNotFoundException {
        return peService.getPEbyID(id, user);
    }

    @GetMapping("/name/{name}")
    public PE getPEbyName(@PathVariable(value = "name") String name, @PathVariable String user) throws EntityNotFoundException {
        return peService.getPEByName(name, user);
    }

    @GetMapping("/all")
    public List<PE> getAllPEs(@PathVariable String user) {
        return peService.getAllPEs(user);
    }

    @DeleteMapping("remove/id/{id}")
    public int removePEbyId(@PathVariable(value = "id") Long id, @PathVariable String user) {
        return peService.removePEbyID(id, user);
    }

    @DeleteMapping("remove/name/{name}")
    public int removePEbyName(@PathVariable(value = "name") String name, @PathVariable String user) {
        return peService.removePEByName(name, user);
    }

}
