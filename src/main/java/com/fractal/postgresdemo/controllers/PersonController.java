package com.fractal.postgresdemo.controllers;

import com.fractal.postgresdemo.exxception.PersonNotFoundException;
import com.fractal.postgresdemo.model.Person;
import com.fractal.postgresdemo.service.SimulatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {
    private SimulatorService simulatorService;
    private HttpHeaders headers;

    @Autowired
    public PersonController(SimulatorService simulatorService){
        this.simulatorService = simulatorService;
        headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    }

    @GetMapping("getAllPersons")
    public ResponseEntity<String> getAllPersons(){
        List<Person> person = simulatorService.getAllPersons();
        System.out.println(person);
        //MappingJacksonValue mappingJacksonValue = new MappingJacksonValue()
        headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity(person, headers, HttpStatus.OK);
    }

    @PostMapping("addPerson")
    public ResponseEntity<Person> addPerson(@RequestBody Person person){

        long queryResult = simulatorService.addPerson(person);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .replacePath("/person/getPersonById/{id}")
                .buildAndExpand(queryResult).toUri();

        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("deletePerson/{id}")
    public void deletePerson(@PathVariable long id){
        System.out.println("In Delete");
        if(!simulatorService.deletePerson(id))
           throw new PersonNotFoundException("id : " + id);
    }

    @GetMapping("getPersonById/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id){
        System.out.println("was i here");
        Person person = simulatorService.getPersonById(id);
        System.out.println(person);
        return new ResponseEntity<Person>(person, headers,HttpStatus.OK);
    }

    @PutMapping("updatePerson")
    public void updatePerson(@RequestBody Person person){
        if(!simulatorService.updatePerson(person))
            throw new PersonNotFoundException("id : " + person.getId());
    }

   @GetMapping("justcheck")
    public ResponseEntity<String> justCheck(){
        System.out.println("Kamaal hain");
        return new ResponseEntity<String>("ok bro", headers, HttpStatus.OK);
   }
}
