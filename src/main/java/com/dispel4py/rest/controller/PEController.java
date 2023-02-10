package com.dispel4py.rest.controller;
import com.dispel4py.rest.error.EntityExistsException;
import com.dispel4py.rest.error.EntityNotFoundException;
import com.dispel4py.rest.model.PE;
import com.dispel4py.rest.service.PEService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/registry/pe")
public class PEController {

    private PEService peService;

    public PEController(PEService peService) {
        this.peService = peService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public PE register(@RequestBody PE pe) throws EntityExistsException {
        return peService.registerPE(pe);
    }

    @GetMapping("/id/{id}")
    public PE getPEbyId(@PathVariable(value="id") Long id) throws EntityNotFoundException {
        return peService.getPEbyID(id);
    }

    @GetMapping("/name/{name}")
    public PE getPEbyName(@PathVariable(value="name") String name)throws EntityNotFoundException {
        return peService.getPEByName(name);
    }

    @GetMapping("/all")
    public List<PE> getAllPEs(){return peService.getAllPEs();}

    @DeleteMapping("remove/id/{id}")
    public int removePEbyId(@PathVariable(value="id") Long id){
        return peService.removePEbyID(id);
    }
    @DeleteMapping("remove/name/{name}")
    public int removePEbyName(@PathVariable(value="name") String name){
        return peService.removePEByName(name);
    }

}
